/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.security.jakartasec.fat.config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import componenttest.custom.junit.runner.AlwaysPassesTest;
import io.openliberty.security.jakartasec.fat.config.tests.ConfigurationClaimsDefinitionTests;
import io.openliberty.security.jakartasec.fat.config.tests.ConfigurationELValuesOverrideTests;
import io.openliberty.security.jakartasec.fat.config.tests.ConfigurationELValuesOverrideWithoutHttpSessionTests;
import io.openliberty.security.jakartasec.fat.config.tests.ConfigurationTests;

@RunWith(Suite.class)
@SuiteClasses({
                AlwaysPassesTest.class,
                ConfigurationTests.class,
                ConfigurationClaimsDefinitionTests.class,
                ConfigurationELValuesOverrideTests.class,
                ConfigurationELValuesOverrideWithoutHttpSessionTests.class
})
public class FATSuite {

}
