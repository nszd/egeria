/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.assetconsumer.server;

import org.odpi.openmetadata.accessservices.assetconsumer.ffdc.AssetConsumerErrorCode;
import org.odpi.openmetadata.accessservices.assetconsumer.handlers.GlossaryHandler;
import org.odpi.openmetadata.accessservices.assetconsumer.handlers.LoggingHandler;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.commonservices.multitenant.OCFOMASServiceInstance;
import org.odpi.openmetadata.commonservices.multitenant.ffdc.exceptions.NewInstanceException;
import org.odpi.openmetadata.repositoryservices.auditlog.OMRSAuditLog;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;

import java.util.List;

/**
 * AssetConsumerServicesInstance caches references to objects for a specific server.
 * It is also responsible for registering itself in the instance map.
 */
public class AssetConsumerServicesInstance extends OCFOMASServiceInstance
{
    private static AccessServiceDescription myDescription = AccessServiceDescription.ASSET_CONSUMER_OMAS;

    private GlossaryHandler glossaryHandler;
    private LoggingHandler loggingHandler;

    /**
     * Set up the handlers for this server.
     *
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that AssetConsumer is allowed to serve Assets from.
     * @param auditLog destination for audit log events.
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize maximum number of results that can be returned on a single call
     * @throws NewInstanceException a problem occurred during initialization
     */
    public AssetConsumerServicesInstance(OMRSRepositoryConnector repositoryConnector,
                                         List<String>            supportedZones,
                                         OMRSAuditLog            auditLog,
                                         String                  localServerUserId,
                                         int                     maxPageSize) throws NewInstanceException
    {
        super(myDescription.getAccessServiceName() + " OMAS",
              repositoryConnector,
              supportedZones,
              null,
              auditLog,
              localServerUserId,
              maxPageSize);

        final String methodName = "new ServiceInstance";

        if (repositoryHandler != null)
        {
            glossaryHandler = new GlossaryHandler(serviceName,
                                                  serverName,
                                                  invalidParameterHandler,
                                                  repositoryHelper,
                                                  repositoryHandler);
            loggingHandler = new LoggingHandler(auditLog);
        }
        else
        {
            AssetConsumerErrorCode errorCode    = AssetConsumerErrorCode.OMRS_NOT_INITIALIZED;
            String                 errorMessage = errorCode.getErrorMessageId() + errorCode.getFormattedErrorMessage(methodName);

            throw new NewInstanceException(errorCode.getHTTPErrorCode(),
                                           this.getClass().getName(),
                                           methodName,
                                           errorMessage,
                                           errorCode.getSystemAction(),
                                           errorCode.getUserAction());

        }
    }


    /**
     * Return the specialized glossary handler for Asset Consumer OMAS.
     *
     * @return glossary handler
     */
    GlossaryHandler getGlossaryHandler()
    {
        return glossaryHandler;
    }


    /**
     * Return the specialized handler for logging messages to the audit log.
     *
     * @return logging handler
     */
    LoggingHandler getLoggingHandler()
    {
        return loggingHandler;
    }

}
