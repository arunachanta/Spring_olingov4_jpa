package com.walmart.rebates.core.providers;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.server.api.uri.UriInfo;

public interface EntityProvider {

	CsdlEntityType getEntityType();

	String getEntitySetName();

	CsdlEntitySet getEntitySet(UriInfo uriInfo);

	/**
	 * Gets the fully qualified name.
	 *
	 * @return the fully qualified name
	 */
	FullQualifiedName getFullyQualifiedName();
}
