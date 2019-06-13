package com.walmart.rebates.core.providers;

import org.apache.olingo.commons.api.data.AbstractEntityCollection;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.server.api.uri.UriInfo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.*;

import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.walmart.rebates.core.foundation.EntityProvider;
import com.walmart.rebates.dao.Product;
import com.walmart.rebates.repository.ProductRepository;

@Component
public class ProductEntityProvider implements EntityProvider {

	@Autowired
	ProductRepository repoProd;
	
	// Service Namespace
	public static final String NAMESPACE = "com.example.model";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(
			NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_CATEGORY_NAME = "Product";
	public static final FullQualifiedName ET_CATEGORY_FQN = new FullQualifiedName(
			NAMESPACE, ET_CATEGORY_NAME);

	// Entity Set Names
	public static final String ES_CATEGORIES_NAME = "Products";

	@Override
	public CsdlEntityType getEntityType() {
		
		// create EntityType properties
		CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		CsdlProperty name = new CsdlProperty().setName("Name").setType(
				EdmPrimitiveTypeKind.String.getFullQualifiedName());
		CsdlProperty description = new CsdlProperty().setName("Description").setType(
				EdmPrimitiveTypeKind.String.getFullQualifiedName());

		// create PropertyRef for Key element
		CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		propertyRef.setName("ID");

		// configure EntityType
		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(ET_CATEGORY_NAME);
		entityType.setProperties(Arrays.asList(id, name, description));
		entityType.setKey(Arrays.asList(propertyRef));
		

		return entityType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rohitghatol.spring.odata.edm.providers.EntityProvider#getEntitySet
	 * (org.apache.olingo.server.api.uri.UriInfo)
	 */
	@Override
	public AbstractEntityCollection getEntitySet(UriInfo uriInfo) {
		List<UriResource> resourcePaths = uriInfo.getUriResourceParts();

		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths
				.get(0); // in our example, the first segment is the EntitySet

		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

		AbstractEntityCollection entitySet = getData(edmEntitySet);

		return entitySet;
	}

	/**
	 * Helper method for providing some sample data.
	 *
	 * @param edmEntitySet
	 *            for which the data is requested
	 * @return data of requested entity set
	 */
	private EntityCollection getData(EdmEntitySet edmEntitySet) {
		EntityCollection productsCollection = new EntityCollection();
        // check for which EdmEntitySet the data is requested
            List<Entity> productList = productsCollection.getEntities();

            Iterable<Product> products = repoProd.findAll();
            for (Product product : products) {
                Entity entity = new Entity().addProperty(new Property(null, "ID", ValueType.PRIMITIVE, product.getId()))
                        .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, product.getName()));
                entity.setId(createId("Products",product.getId()));
                productList.add(entity);
            }
            return productsCollection;
        }
        

	@Override
	public String getEntitySetName() {		
		return ES_CATEGORIES_NAME;
	}

    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }


	/* (non-Javadoc)
	 * @see com.rohitghatol.spring.odata.edm.providers.EntityProvider#getFullyQualifiedName()
	 */
	@Override
	public FullQualifiedName getFullyQualifiedName() {
		return ET_CATEGORY_FQN;
	}

}