package com.walmart.rebates.processor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ServiceMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.walmart.rebates.core.foundation.GenericEntityCollectionProcessor;
import com.walmart.rebates.core.providers.ProductEdmProvider;
import com.walmart.rebates.dao.Product;
import com.walmart.rebates.repository.ProductRepository;

/**
 * This class is invoked by the Olingo framework when the the OData service is invoked order to display a list/collection of data (entities).
 * This is the case if an EntitySet is requested by the user.
 * Such an example URL would be:
 * http://localhost:8080/odata/Products
 */

@Component
@ComponentScan("com.walmart.rebates.repository")
public class ProductEntityProcessor extends GenericEntityCollectionProcessor {

	
	@Autowired
	ProductRepository repoProd;
	
    /**
     * Helper method for providing some sample data
     *
     * @param edmEntitySet for which the data is requested
     * @return data of requested entity set
     */
    public EntityCollection getData(EdmEntitySet edmEntitySet) {

        EntityCollection productsCollection = new EntityCollection();
        // check for which EdmEntitySet the data is requested
        if (ProductEdmProvider.ES_PRODUCTS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = productsCollection.getEntities();

            Iterable<Product> products = repoProd.findAll();
            for (Product product : products) {
                Entity entity = new Entity().addProperty(new Property(null, "ID", ValueType.PRIMITIVE, product.getId()))
                        .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, product.getName()));
                entity.setId(createId("Products",1));
                productList.add(entity);
            }
        }

        return productsCollection;
    }

    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }
}
