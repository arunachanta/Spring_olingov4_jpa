package com.walmart.rebates.core.foundation;

import org.apache.olingo.commons.api.data.AbstractEntityCollection;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;

import org.apache.olingo.server.api.uri.UriInfo;

public interface EntityProvider {

	
	CsdlEntityType getEntityType();

	String getEntitySetName();
	
	AbstractEntityCollection getEntitySet(UriInfo uriInfo);
	
	/**
	 * Gets the fully qualified name.
	 *
	 * @return the fully qualified name
	 */
	FullQualifiedName getFullyQualifiedName();
}