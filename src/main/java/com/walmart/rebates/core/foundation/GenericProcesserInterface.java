package com.walmart.rebates.core.foundation;

import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;

public interface GenericProcesserInterface extends EntityCollectionProcessor {

	public EntityCollection getData(EdmEntitySet edmEntitySet) ;
	
}
