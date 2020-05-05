/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.grpc.client.security;

import com.ibm.ws.grpc.client.config.GrpcClientConfigHolder;

import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;

public class LibertyGrpcClientOutSSLSupport {
	
	/**
	 * TODO: map the Liberty ssl config to SslContext object
	 * @param target
	 * @return
	 */
	public static SslContext getOutboundClientSSLContext(String target) {
		
		String sslRef = GrpcClientConfigHolder.getSSLConfig(target);
		SslContext context = null;
		
		/* 
		 * TODO: actually map sslRef to the Liberty SSL config
		 * see LibertyJaxRsClientSSLOutInterceptor
		 * 
		context = GrpcSslContexts.forClient()
			    // if server's cert doesn't chain to a standard root
			    .trustManager(caFile)
			    .keyManager(clientCertFile, keyFile)
			    . etc.... 
			    .build();
		*/
		
		return context;
	}
}
