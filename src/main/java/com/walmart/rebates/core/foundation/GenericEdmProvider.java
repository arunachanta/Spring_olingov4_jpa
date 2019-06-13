/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.walmart.rebates.core.foundation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.apache.olingo.commons.api.edm.provider.*;


@Component
public class GenericEdmProvider extends CsdlAbstractEdmProvider {

	@Autowired
	private ApplicationContext ctx;

	// Service Namespace
	public static final String NAMESPACE = "com.example.model";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(
			NAMESPACE, CONTAINER_NAME);

	@Override
	public List<CsdlSchema> getSchemas() throws ODataException {

		// create Schema
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);

		Map<String, EntityProvider> entityProviders = ctx
				.getBeansOfType(EntityProvider.class);
		


		// add EntityTypes
		List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		for (String entity : entityProviders.keySet()) {

			EntityProvider entityProvider = entityProviders.get(entity);
			entityTypes.add(entityProvider.getEntityType());

		}

		schema.setEntityTypes(entityTypes);

		// add EntityContainer
		schema.setEntityContainer(getEntityContainer());

		// finally
		List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		schemas.add(schema);

		return schemas;
	}

	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName)
			throws ODataException {

		CsdlEntityType result = null;
		Map<String, EntityProvider> entityProviders = ctx
				.getBeansOfType(EntityProvider.class);

		for (String entity : entityProviders.keySet()) {

			EntityProvider entityProvider = entityProviders.get(entity);
			CsdlEntityType entityType = entityProvider.getEntityType();
			if (entityType.getName().equals(entityTypeName.getName())) {
				result = entityType;
				break;
			}

		}
		return result;

	}

	@Override
	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer,
			String entitySetName) throws ODataException {

		CsdlEntitySet result = null;
		Map<String, EntityProvider> entityProviders = ctx
				.getBeansOfType(EntityProvider.class);

		for (String entity : entityProviders.keySet()) {

			EntityProvider entityProvider = entityProviders.get(entity);
			CsdlEntityType entityType = entityProvider.getEntityType();
			if (entityProvider.getEntitySetName().equals(entitySetName)) {
				result = new CsdlEntitySet();
				result.setName(entityProvider.getEntitySetName());
				result.setType(entityProvider.getFullyQualifiedName());
				break;
			}

		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.olingo.server.api.edm.provider.EdmProvider#getEntityContainer
	 * ()
	 */
	@Override
	public CsdlEntityContainer getEntityContainer() throws ODataException {

		// create EntitySets
		List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		
		Map<String, EntityProvider> entityProviders = ctx
				.getBeansOfType(EntityProvider.class);

		for (String entity : entityProviders.keySet()) {
			EntityProvider entityProvider = entityProviders.get(entity);
			entitySets.add(getEntitySet(CONTAINER, entityProvider.getEntitySetName()));
		}
		
		

		// create EntityContainer
		CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		entityContainer.setName(CONTAINER_NAME);
		entityContainer.setEntitySets(entitySets);
		

		return entityContainer;
	}

	@Override
	public CsdlEntityContainerInfo getEntityContainerInfo(
			FullQualifiedName entityContainerName) throws ODataException {

		// This method is invoked when displaying the service document at e.g.
		// http://localhost:8080/DemoService/DemoService.svc
		if (entityContainerName == null
				|| entityContainerName.equals(CONTAINER)) {
			CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
			entityContainerInfo.setContainerName(CONTAINER);
			return entityContainerInfo;
		}

		return null;
	}
}