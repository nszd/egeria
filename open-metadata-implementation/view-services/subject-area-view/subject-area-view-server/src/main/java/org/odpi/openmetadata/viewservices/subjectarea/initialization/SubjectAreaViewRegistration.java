/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.subjectarea.initialization;

import org.odpi.openmetadata.adminservices.configuration.OMAGViewServiceRegistration;
import org.odpi.openmetadata.adminservices.configuration.registration.ServiceOperationalStatus;
import org.odpi.openmetadata.adminservices.configuration.registration.ServiceRegistration;
import org.odpi.openmetadata.adminservices.configuration.registration.ViewServiceDescription;
import org.odpi.openmetadata.viewservices.subjectarea.admin.SubjectAreaViewAdmin;

/**
 * SubjectAreaRegistration registers the view service with the OMAG Server administration services.
 * This registration must be driven once at server start up.  The OMAG Server administration services
 * then use this registration information as confirmation that there is an implementation of this
 * view service in the server and it can be configured and used.
 */
public class SubjectAreaViewRegistration
{
    /**
     * Pass information about this view service to the OMAG Server administration services.
     */
    public static void registerViewService()
    {
        ViewServiceDescription myDescription = ViewServiceDescription.SUBJECT_AREA;
        ServiceRegistration myRegistration = new ServiceRegistration(myDescription,
                ServiceOperationalStatus.ENABLED,
                SubjectAreaViewAdmin.class.getName());
        OMAGViewServiceRegistration.registerViewService(myRegistration);
    }
}
