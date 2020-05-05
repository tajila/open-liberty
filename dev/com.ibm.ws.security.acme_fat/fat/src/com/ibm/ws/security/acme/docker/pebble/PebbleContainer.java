/*******************************************************************************
 * Copyright (c) 2019, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.security.acme.docker.pebble;

import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.testcontainers.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.images.builder.ImageFromDockerfile;

import com.github.dockerjava.api.model.ContainerNetwork;
import com.ibm.websphere.simplicity.log.Log;
import com.ibm.ws.security.acme.docker.CAContainer;

/**
 * Testcontainer implementation for the letsencrypt/pebble container.
 * 
 * <p/>
 * This testcontainer contains both the Pebble ACME compliant CA server as well
 * as the challtestsrv server servering as a mock DNS server. The container is
 * initialized to return all HTTP-01 challenges to the client IP address.
 */
public class PebbleContainer extends CAContainer {
	/**
	 * The mock DNS server port.
	 */
	public final int DNS_PORT = 8053;

	/**
	 * The REST management port.
	 */
	public final int CHALL_MANAGEMENT_PORT = 8055;

	private Network network = Network.newNetwork();

	public final GenericContainer<?> challtestsrv = new GenericContainer<>("letsencrypt/pebble-challtestsrv")
			.withCommand("pebble-challtestsrv").withExposedPorts(DNS_PORT, CHALL_MANAGEMENT_PORT).withNetwork(network)
			.withLogConsumer(o -> System.out.print("[CHL] " + o.getUtf8String()));

	/**
	 * Log the output from this testcontainer.
	 * 
	 * @param frame
	 *            The frame containing log data.
	 */
	public static void log(OutputFrame frame) {
		String msg = frame.getUtf8String();
		if (msg.endsWith("\n"))
			msg = msg.substring(0, msg.length() - 1);
		Log.info(PebbleContainer.class, "pebble", msg);
	}

	/**
	 * Instantiate a new {@link PebbleContainer} instance.
	 * 
	 * @param dnsServer
	 *            Address of the DNS server to use to make DNS lookups for
	 *            domains.
	 */
	public PebbleContainer() {
		super(new ImageFromDockerfile()
				.withDockerfileFromBuilder(builder -> builder.from("letsencrypt/pebble")
						.copy("pebble-config.json", "/test/config/pebble-config.json").build())
				.withFileFromFile("pebble-config.json", new File("lib/LibertyFATTestFiles/pebble-config.json")), 5002,
				14000, 15000);

		challtestsrv.start();

		String dnsServer = getIntraContainerIP() + ":" + DNS_PORT;

		this.withCommand("pebble", "-dnsserver", dnsServer, "-config", "/test/config/pebble-config.json", "-strict",
				"false");
		this.withExposedPorts(getDnsManagementPort(), getAcmeListenPort());
		this.withNetwork(network);
		this.withLogConsumer(PebbleContainer::log);

		Testcontainers.exposeHostPorts(5002);

		start();

		try {
			/*
			 * Default responses to the client host.
			 */
			setDnsDefaultIpv4(getClientHost());

			/*
			 * Disable the IPv6 responses. The Pebble CA server responds on AAAA
			 * (IPv6) responses before A (IPv4) responses, and we don't
			 * currently have the testcontainer host's IPv6 address.
			 */
			setDnsDefaultIpv6("");
		} catch (IOException e) {
			throw new IllegalStateException("Failed to set default mock DNS A and AAAA record IP addresses.", e);
		}

		Log.info(PebbleContainer.class, "PebbleContainer", "ContainerIpAddress: " + getContainerIpAddress());
		Log.info(PebbleContainer.class, "PebbleContainer", "DockerImageName:    " + getDockerImageName());
		Log.info(PebbleContainer.class, "PebbleContainer", "ContainerInfo:      " + getContainerInfo());
	}

	@Override
	public void stop() {
		challtestsrv.stop();
		super.stop();
		network.close();
	}

	@Override
	public String getAcmeDirectoryURI(boolean usePebbleURI) {
		if (usePebbleURI) {
			/*
			 * The "acme://pebble/<host>:<port>" will tell acme4j to load the
			 * PebbleAcmeProvider and PebbleHttpConnector, which will trust
			 * Pebble's static self-signed certificate.
			 */
			return "acme://pebble/" + this.getContainerIpAddress() + ":" + this.getMappedPort(getAcmeListenPort());
		} else {
			/*
			 * This will cause acme4j to use the GenericAcmeProvider.
			 */
			return "https://" + this.getContainerIpAddress() + ":" + this.getMappedPort(getAcmeListenPort()) + "/dir";
		}
	}

	@Override
	protected String getIntraContainerIP() {
		/*
		 * Get the IP address for the challtestsrv container as seen from the
		 * container network.
		 */
		String intraContainerIpAddress = null;
		for (Entry<String, ContainerNetwork> entry : challtestsrv.getContainerInfo().getNetworkSettings().getNetworks()
				.entrySet()) {
			intraContainerIpAddress = entry.getValue().getIpAddress();
			break;
		}
		if (intraContainerIpAddress == null) {
			fail("Didn't find IP address for challtestsrv server.");
		}

		return intraContainerIpAddress;
	}

	@Override
	protected String getDnsManagementAddress() {
		return "http://" + challtestsrv.getContainerIpAddress() + ":"
				+ challtestsrv.getMappedPort(CHALL_MANAGEMENT_PORT);
	}
}
