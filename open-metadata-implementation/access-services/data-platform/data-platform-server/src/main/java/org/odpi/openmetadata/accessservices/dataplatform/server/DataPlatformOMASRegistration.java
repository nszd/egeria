/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.dataplatform.server;

import org.odpi.openmetadata.accessservices.dataplatform.admin.DataPlatformAdmin;
import org.odpi.openmetadata.adminservices.configuration.OMAGAccessServiceRegistration;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.adminservices.configuration.registration.ServiceOperationalStatus;
import org.odpi.openmetadata.adminservices.configuration.registration.ServiceRegistration;


/**
 * DataPlatformOMASRegistration registers the access service with the OMAG Server administration services.
 * This registration must be driven once at server start up.  The OMAG Server administration services
 * then use this registration information as confirmation that there is an implementation of this
 * access service in the server and it can be configured and used.
 */
public class DataPlatformOMASRegistration {

    /**
     * Pass information about this access service to the OMAG Server administration services.
     */
    public static void registerAccessService() {

            AccessServiceDescription myDescription = AccessServiceDescription.DATA_PLATFORM_OMAS;
            ServiceRegistration myRegistration = new ServiceRegistration(myDescription,
                    ServiceOperationalStatus.ENABLED,
                    DataPlatformAdmin.class.getName()
            );
            OMAGAccessServiceRegistration.registerAccessService(myRegistration);
        }
}


