package com.walmart.rebates.core.foundation;


import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;

/**
 * this class is supposed to declare the metadata of the OData service
 * it is invoked by the Olingo framework e.g. when the metadata document of the service is invoked
 * e.g. http://localhost:8080/ExampleService1/ExampleService1.svc/$metadata
 */
//@Component
public class GenericEdmProvider extends CsdlAbstractEdmProvider {
	public FullQualifiedName objectFQDN;
	public String entitySetName;
	public String entityName;
	/*
	 * public GenericEdmProvider() { this.objectFQDN = objectFQDN; // TODO
	 * Auto-generated constructor stub }
	 */

	// Service Namespace
    public static final String NAMESPACE = "com.rebate.model";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);



    @Override
    public List<CsdlSchema> getSchemas() throws ODataException {
        // create Schema
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);

        // add EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
        entityTypes.add(getEntityType(objectFQDN));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
        schemas.add(schema);

        return schemas;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() throws ODataException {
        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
        entitySets.add(getEntitySet(CONTAINER, entitySetName));

        // create EntityContainer
        CsdlEntityContainer entityContainer = new CsdlEntityContainer();
        entityContainer.setName(CONTAINER_NAME);
        entityContainer.setEntitySets(entitySets);
        return entityContainer;
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {
    	CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
        // This method is invoked when displaying the service document at e.g. http://localhost:8080/DemoService/DemoService.svc
        if(entityContainerName == null || entityContainerName.equals(CONTAINER)){
            
            entityContainerInfo.setContainerName(CONTAINER);
            
        }
        return entityContainerInfo;
    }
    
    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {

        if(entityContainer.equals(CONTAINER)){
            if(entitySetName.equals(entitySetName)){
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(entitySetName);
                entitySet.setType(objectFQDN);

                return entitySet;
            }
        }

        return null;
    }

    public void setObjectFQDN(FullQualifiedName objectFQDN,String entityName,String entitySetName) {
    	this.objectFQDN = objectFQDN;
    	this.entityName = entityName;
    	this.entitySetName = entitySetName;
    	
    }
}
