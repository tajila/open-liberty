/*******************************************************************************
 * Copyright (c) 2015, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.security.saml.fat.jaxrs;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.ws.security.fat.common.actions.SecurityTestFeatureEE9RepeatAction;
import com.ibm.ws.security.fat.common.actions.SecurityTestRepeatAction;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedAPITests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedBasic1ServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedBasic2ServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedPkixWithCertInServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedPkixWithoutCertInServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedTrustedIssuers1ServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.IDPInitiated.RSSamlIDPInitiatedTrustedIssuers2ServerTests;
import com.ibm.ws.security.saml.fat.jaxrs.SPInitiated.RSSamlSolicitedSPInitiatedAPITests;

import componenttest.rules.repeater.EmptyAction;
import componenttest.rules.repeater.RepeatTests;

@RunWith(Suite.class)
@SuiteClasses({

        RSSamlIDPInitiatedBasic1ServerTests.class,
        RSSamlIDPInitiatedBasic2ServerTests.class,
        RSSamlIDPInitiatedAPITests.class,
        RSSamlSolicitedSPInitiatedAPITests.class,
        RSSamlIDPInitiatedPkixWithCertInServerTests.class,
        RSSamlIDPInitiatedPkixWithoutCertInServerTests.class,
        RSSamlIDPInitiatedTrustedIssuers1ServerTests.class,
        RSSamlIDPInitiatedTrustedIssuers2ServerTests.class

})
/**
 * Purpose: This suite collects and runs all known good test suites.
 */
public class FATSuite {

    @ClassRule
    public static RepeatTests repeat = RepeatTests.with(new EmptyAction().liteFATOnly())
            .andWith(new SecurityTestRepeatAction().onlyOnWindows().fullFATOnly())
            .andWith(new SecurityTestFeatureEE9RepeatAction().notOnWindows().fullFATOnly());

    @BeforeClass
    public static void setup() throws Exception {
        /*
         * Force the tests to use local LDAP server
         */
        System.setProperty("fat.test.really.use.local.ldap", "true");
    }

}
