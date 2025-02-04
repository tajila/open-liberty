/*******************************************************************************
 * Copyright (c) 2019,2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.health.tck;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.websphere.simplicity.PortType;

import componenttest.annotation.AllowedFFDC;
import componenttest.annotation.Server;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.utils.tck.TCKResultsInfo.Type;
import componenttest.topology.utils.tck.TCKUtils;

/**
 * This is a test class that runs a whole Maven TCK as one test FAT test.
 * There is a detailed output on specific
 */
@RunWith(FATRunner.class)
public class HealthTCKLauncher {

    @Server("HealthTCKServer")
    public static LibertyServer server;

    @BeforeClass
    public static void setUp() throws Exception {
        server.startServer("console.log", true, true);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        server.stopServer("CWMH0051W", "CWWKZ0002E", "SRVE0190E", "CWWKZ0014W");
    }

    @Test
    @AllowedFFDC // The tested deployment exceptions cause FFDC so we have to allow for this.
    public void launchHealth10Tck() throws Exception {
        String protocol = "http";
        String host = server.getHostname();
        String port = Integer.toString(server.getPort(PortType.WC_defaulthost));

        Map<String, String> additionalProps = new HashMap<>();
        additionalProps.put("test.url", protocol + "://" + host + ":" + port);

        String bucketName = "com.ibm.ws.microprofile.health.1.0_fat_tck";
        String testName = this.getClass() + ":launchHealth10Tck";
        Type type = Type.MICROPROFILE;
        String specName = "Health";
        String specVersion = "1.0";
        TCKUtils.runTCKMvnCmd(server, bucketName, testName, type, specName, specVersion, additionalProps);
    }

}
