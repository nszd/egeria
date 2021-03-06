/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.glossaryauthor.initialization;

import org.odpi.openmetadata.accessservices.subjectarea.client.nodes.categories.SubjectAreaCategory;
import org.odpi.openmetadata.accessservices.subjectarea.client.nodes.glossaries.SubjectAreaGlossary;
import org.odpi.openmetadata.accessservices.subjectarea.client.nodes.projects.SubjectAreaProject;
import org.odpi.openmetadata.accessservices.subjectarea.client.nodes.terms.SubjectAreaTerm;
import org.odpi.openmetadata.accessservices.subjectarea.client.relationships.SubjectAreaRelationship;
import org.odpi.openmetadata.adminservices.configuration.registration.ViewServiceDescription;
import org.odpi.openmetadata.commonservices.ffdc.exceptions.PropertyServerException;
import org.odpi.openmetadata.commonservices.multitenant.OMVSServiceInstanceHandler;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.viewservices.glossaryauthor.admin.serviceinstances.GlossaryAuthorViewServicesInstance;
import org.odpi.openmetadata.viewservices.glossaryauthor.handlers.*;

/**
 * GlossaryAuthorViewInstanceHandler retrieves information from the instance map for the
 * access service instances.  The instance map is thread-safe.  Instances are added
 * and removed by the GlossaryAuthorViewAdmin class.
 */
public class GlossaryAuthorViewInstanceHandler extends OMVSServiceInstanceHandler {

    private static GlossaryAuthorViewServicesInstanceMap instanceMap = new GlossaryAuthorViewServicesInstanceMap();

    /**
     * Default constructor registers the view service
     */
    public GlossaryAuthorViewInstanceHandler() {
        super(ViewServiceDescription.GLOSSARY_AUTHOR.getViewServiceName());
        GlossaryAuthorViewRegistration.registerViewService();
    }

    public GlossaryHandler getGlossaryHandler(String serverName, String userId, String serviceOperationName) throws UserNotAuthorizedException, PropertyServerException, InvalidParameterException {

        SubjectAreaGlossary subjectAreaGlossary = this.getSubjectAreaGlossary(serverName, userId, serviceOperationName);
        return new GlossaryHandler(subjectAreaGlossary);
    }

    public ProjectHandler getProjectHandler(String serverName, String userId, String serviceOperationName) throws UserNotAuthorizedException, PropertyServerException, InvalidParameterException {

        SubjectAreaProject subjectAreaProject = this.getSubjectAreaProject(serverName, userId, serviceOperationName);
        return new ProjectHandler(subjectAreaProject);
    }

    public RelationshipHandler getRelationshipHandler(String serverName, String userId, String serviceOperationName) throws UserNotAuthorizedException, PropertyServerException, InvalidParameterException {

        SubjectAreaRelationship subjectAreaRelationship = this.getSubjectAreaRelationship(serverName, userId, serviceOperationName);
        return new RelationshipHandler(subjectAreaRelationship);
    }

    public TermHandler getTermHandler(String serverName, String userId, String serviceOperationName) throws UserNotAuthorizedException, PropertyServerException, InvalidParameterException {

        SubjectAreaTerm subjectAreaTerm = this.getSubjectAreaTerm(serverName, userId, serviceOperationName);
        return new TermHandler(subjectAreaTerm);
    }

    public CategoryHandler getCategoryHandler(String serverName, String userId, String serviceOperationName) throws UserNotAuthorizedException, PropertyServerException, InvalidParameterException {

        SubjectAreaCategory subjectAreaCategory = this.getSubjectAreaCategory(serverName, userId, serviceOperationName);
        return new CategoryHandler(subjectAreaCategory);
    }

    /**
     * This method returns the object for the tenant to use to work with the
     * subject area glossary API
     *
     * @param serverName           name of the server that the request is for
     * @param userId               local server userid
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaGlossary subject area glossary API object
     */
    private SubjectAreaGlossary getSubjectAreaGlossary(String serverName, String userId, String serviceOperationName)
    throws
    InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        GlossaryAuthorViewServicesInstance instance = getSubjectAreaViewServicesInstance(userId, serverName, serviceOperationName);
        return instance.getSubjectAreaGlossary();
    }

    /**
     * This method returns the object for the tenant to use to work with the
     * subject area term API
     *
     * @param serverName           name of the server that the request is for
     * @param userId               local server userid
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaGlossary subject area glossary API object
     */
    private SubjectAreaTerm getSubjectAreaTerm(String serverName, String userId, String serviceOperationName)
    throws
    InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        GlossaryAuthorViewServicesInstance instance = getSubjectAreaViewServicesInstance(userId, serverName, serviceOperationName);
        return instance.getSubjectAreaTerm();
    }

    /**
     * This method returns the object for the tenant to use to work with the
     * subject area category API
     *
     * @param serverName           name of the server that the request is for
     * @param userId               local server userid
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaGlossary subject area glossary API object
     */
    private SubjectAreaCategory getSubjectAreaCategory(String serverName, String userId, String serviceOperationName)
    throws
    InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        GlossaryAuthorViewServicesInstance instance = getSubjectAreaViewServicesInstance(userId, serverName, serviceOperationName);
        return instance.getSubjectAreaCategory();
    }

    /**
     * This method returns the object for the tenant to use to work with the
     * subject area project API
     *
     * @param serverName           name of the server that the request is for
     * @param userId               local server userid
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaProject subject area glossary API object
     */
    private SubjectAreaProject getSubjectAreaProject(String serverName, String userId, String serviceOperationName)
    throws
    InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        GlossaryAuthorViewServicesInstance instance = getSubjectAreaViewServicesInstance(userId, serverName, serviceOperationName);
        return instance.getSubjectAreaProject();
    }

    /**
     * This method returns the object for the tenant to use to work with the
     * subject area relationship API
     *
     * @param serverName           name of the server that the request is for
     * @param userId               local server userid
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaRelationship subject area glossary API object
     */
    private SubjectAreaRelationship getSubjectAreaRelationship(String serverName, String userId, String serviceOperationName)
    throws InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        GlossaryAuthorViewServicesInstance instance = getSubjectAreaViewServicesInstance(userId, serverName, serviceOperationName);
        return instance.getSubjectAreaRelationship();
    }

    /**
     * Get the subject area services instance. This is an instance associated with the UI servername (tenant).
     *
     * @param userId               local server userid
     * @param serverName           name of the server that the request is for
     * @param serviceOperationName service operation - usually the top level rest call
     * @return SubjectAreaViewServicesInstance instance for this tenant to use.
     */
    private GlossaryAuthorViewServicesInstance getSubjectAreaViewServicesInstance(String userId, String serverName, String serviceOperationName)
    throws InvalidParameterException, PropertyServerException, UserNotAuthorizedException {
        return (GlossaryAuthorViewServicesInstance)
                super.getServerServiceInstance(userId, serverName, serviceOperationName);
    }
}