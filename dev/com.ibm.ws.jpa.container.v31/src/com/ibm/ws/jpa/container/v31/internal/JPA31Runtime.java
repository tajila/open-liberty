/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jpa.container.v31.internal;

import static com.ibm.ws.jpa.management.JPAConstants.JPA_RESOURCE_BUNDLE_NAME;
import static com.ibm.ws.jpa.management.JPAConstants.JPA_TRACE_GROUP;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.SynchronizationType;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.ibm.websphere.csi.J2EEName;
import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.ws.Transaction.UOWCoordinator;
import com.ibm.ws.jpa.JPAPuId;
import com.ibm.ws.jpa.container.v31.JPAEMFactoryV31;
import com.ibm.ws.jpa.container.v31.JPAExEmInvocationV31;
import com.ibm.ws.jpa.container.v31.JPAExEntityManagerV31;
import com.ibm.ws.jpa.container.v31.JPATxEntityManagerV31;
import com.ibm.ws.jpa.management.AbstractJPAComponent;
import com.ibm.ws.jpa.management.JPA20Runtime;
import com.ibm.ws.jpa.management.JPAExEmInvocation;
import com.ibm.ws.jpa.management.JPAExEntityManager;
import com.ibm.ws.jpa.management.JPAPUnitInfo;
import com.ibm.ws.jpa.management.JPARuntime;
import com.ibm.ws.jpa.management.JPATxEntityManager;
import com.ibm.ws.jpa.JPAVersion;

@Component(service = JPARuntime.class,
           property = Constants.SERVICE_RANKING + ":Integer=21")
public class JPA31Runtime extends JPA20Runtime implements JPARuntime {
    private static final TraceComponent tc = Tr.register
                    (JPA31Runtime.class,
                     JPA_TRACE_GROUP,
                     JPA_RESOURCE_BUNDLE_NAME);

    private final static String JEE7_DEFAULT_JTA_DATASOURCE_JNDI = "java:comp/DefaultDataSource";

    @Override
    public JPAVersion getJPARuntimeVersion() {
        return JPAVersion.JPA31;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public EntityManagerFactory createJPAEMFactory(JPAPuId puId, J2EEName j2eeName, EntityManagerFactory emf) {
        return new JPAEMFactoryV31(puId, j2eeName, emf);
    }

    @Override
    public JPATxEntityManager createJPATxEntityManager(JPAPuId puRefId, JPAPUnitInfo puInfo, J2EEName j2eeName, String refName, Map<?, ?> properties,
                                                       boolean isUnsynchronized, AbstractJPAComponent jpaComponent) {
        return new JPATxEntityManagerV31(puRefId, puInfo, j2eeName, refName, properties, isUnsynchronized, jpaComponent);
    }

    @Override
    public JPAExEntityManager createJPAExEntityManager(JPAPuId puRefId, JPAPUnitInfo puInfo, J2EEName j2eeName, String refName, Map<?, ?> properties,
                                                       boolean isUnsynchronized, AbstractJPAComponent jpaComponent) {
        return new JPAExEntityManagerV31(puRefId, puInfo, j2eeName, refName, properties, isUnsynchronized, jpaComponent);
    }

    @Override
    public JPAExEmInvocation createExEmInvocation(UOWCoordinator uowCoord, EntityManager em, boolean txIsUnsynchronized) {
        return new JPAExEmInvocationV31(uowCoord, em, txIsUnsynchronized);
    }

    @Override
    public EntityManager createEntityManagerInstance(EntityManagerFactory emf, boolean unsynchronized) {
        if (emf == null) {
            return null;
        }

        SynchronizationType syncType = unsynchronized ? SynchronizationType.UNSYNCHRONIZED : SynchronizationType.SYNCHRONIZED;

        try {
            return emf.createEntityManager(syncType);
        } catch (AbstractMethodError x) {
            Tr.error(tc, "NOT_COMPLIANT_WITH_JPA21_CWWJP0054E", "EntityManagerFactory", emf.getClass().getName());
            throw x;
        }
    }

    @Override
    public EntityManager createEntityManagerInstance(EntityManagerFactory emf, Map<?, ?> propMap, boolean unsynchronized) {
        if (emf == null) {
            return null;
        }

        SynchronizationType syncType = unsynchronized ? SynchronizationType.UNSYNCHRONIZED : SynchronizationType.SYNCHRONIZED;

        try {
            return emf.createEntityManager(syncType, propMap);
        } catch (AbstractMethodError x) {
            Tr.error(tc, "NOT_COMPLIANT_WITH_JPA21_CWWJP0054E", "EntityManagerFactory", emf.getClass().getName());
            throw x;
        }
    }

    @Override
    public String processJEE7JTADataSource(String jtaDataSource, String nonJtaDataSource) {
        if (jtaDataSource == null && nonJtaDataSource == null) {
            return JEE7_DEFAULT_JTA_DATASOURCE_JNDI;
        }

        return jtaDataSource;
    }

    @Override
    public boolean isIgnoreDataSourceErrors(Boolean ignoreDataSource) {
        return ignoreDataSource != null ? ignoreDataSource : false;
    }
}
