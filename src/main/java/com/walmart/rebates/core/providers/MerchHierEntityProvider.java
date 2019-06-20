package com.walmart.rebates.core.providers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.olingo.commons.api.data.AbstractEntityCollection;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlNavigationProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microsoft.applicationinsights.core.dependencies.javaxannotation.ParametersAreNonnullByDefault;
import com.walmart.rebates.core.foundation.EntityProvider;
import com.walmart.rebates.dao.Category;
import com.walmart.rebates.dao.MerchHier;
import com.walmart.rebates.repository.MercHierRepository;

/**
 * This class is invoked by the Olingo framework when the the OData service is
 * invoked order to display a list/collection of data (entities). This is the
 * case if an EntitySet is requested by the user. Such an example URL would be:
 * http://localhost:8080/Demo/Products
 * 
 * @param <T>
 * @param <T>
 */
@Component
public class MerchHierEntityProvider implements EntityProvider {

	@Autowired
	MercHierRepository mercHierRepository;

	// Service Namespace
	public static final String NAMESPACE = "com.rebate.model";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_MERCHHIER = "MerchHier";
	public static final FullQualifiedName ET_MERCHHIER_FQN = new FullQualifiedName(NAMESPACE, ET_MERCHHIER);
	// Entity Set Names
	public static final String ES_MERCHHIER = "MerchHiers";

	@Override
	public CsdlEntityType getEntityType() {

		CsdlEntityType entityType = new CsdlEntityType();
		entityType.setName(ET_MERCHHIER);

		CsdlNavigationProperty navpop = new CsdlNavigationProperty().setName("MerchHier").setType(ET_MERCHHIER_FQN);
		List<CsdlNavigationProperty> navlist = new ArrayList<CsdlNavigationProperty>();
		navlist.add(navpop);
		entityType.setProperties(setEntityProperties("com.walmart.rebates.dao.MerchHier"));
		entityType.setKey(setkeys("com.walmart.rebates.dao.MerchHier"));
		entityType.setNavigationProperties(navlist);
		return entityType;
	}

	public FullQualifiedName getEDMValue(String primitype) {
		if (primitype == "int") {
			return EdmPrimitiveTypeKind.Int32.getFullQualifiedName();
		}
		if (primitype == "java.lang.String") {
			return EdmPrimitiveTypeKind.String.getFullQualifiedName();
		} else {
			return null;
		}
	}

	public List<CsdlProperty> setEntityProperties(String classname) {
		List<CsdlProperty> list = new ArrayList<CsdlProperty>();
		try {
			Class clsobj = Class.forName(classname);
			Method[] method = clsobj.getMethods();

			for (Method meth : method) {
				if (meth.getName().startsWith("set")) {
					Parameter parameter[] = meth.getParameters();
					for (Parameter param : parameter) {
						String str1 = param.getName();
						String str2 = param.getParameterizedType().getTypeName();
						// final String NAMESPACE = "com.rebates.model";
						// final FullQualifiedName Primitivetpe= new FullQualifiedName(NAMESPACE, str1);
						// EdmPrimitiveTypeKind str3 = EdmPrimitiveTypeKind.valueOfFQN(Primitivetpe);
						FullQualifiedName edmtyp = getEDMValue(str2);
						if (edmtyp != null) {
							CsdlProperty gen = new CsdlProperty().setName(str1).setType(edmtyp);
							list.add(gen);
						} else {
							/*
							 * if (str2.startsWith("com")) { Class clsobj1 = (Class)
							 * param.getParameterizedType(); CsdlMapping mapping = new
							 * CsdlMapping().setInternalName("\"internalName\"").setMappedJavaClass(clsobj1)
							 * ; CsdlProperty gen = new
							 * CsdlProperty().setName(str1).setType(edmtyp).setMapping(mapping);
							 * list.add(gen); }
							 */
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
		}
		return list;
	}

	public List<CsdlPropertyRef> setkeys(String classname) {
		List<CsdlPropertyRef> listobj = new ArrayList<CsdlPropertyRef>();
		Id id;
		Column column;
		String name;
		Class clsobj;
		try {
			clsobj = Class.forName(classname);

			Field[] field = clsobj.getDeclaredFields();
			for (Field f : field) {
				id = f.getAnnotation(Id.class);
				if (id != null) {
					name = f.getName();
					CsdlPropertyRef propertyRef = new CsdlPropertyRef();
					listobj.add(propertyRef.setName(name));
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listobj;

	}

	@Override
	public AbstractEntityCollection getEntitySet(UriInfo uriInfo) {
		List<UriResource> resourcePaths = uriInfo.getUriResourceParts();

		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in our example, the
																									// first segment is
																									// the EntitySet

		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

		AbstractEntityCollection entitySet = getData(edmEntitySet);

		return entitySet;
	}

	private EntityCollection getData(EdmEntitySet edmEntitySet) {
		EntityCollection merchhierCollection = new EntityCollection();
		// check for which EdmEntitySet the data is requested

		List<Entity> merchhierList = merchhierCollection.getEntities();
		Iterable<MerchHier> merchhiers = mercHierRepository.findAll();

		getEntityProperties("com.walmart.rebates.dao.MerchHier", merchhiers, merchhierList);
		return merchhierCollection;

	}

	public <T> void getEntityProperties(String classname, Iterable<T> datadtl, List<Entity> entlist) {
		Entity entity = new Entity();
		String str1;
		String str2 = null;
		Object obj;
		String glbstr1 = null;
		Class clsobj;
		List<Property> proplist = new ArrayList<Property>();
		for (T data : datadtl) {
			Object itemobj = (Object) data;
			clsobj = data.getClass();
			Field[] fld = clsobj.getDeclaredFields();
			Method[] method = clsobj.getMethods();
			for (Method meth : method) {
				if (meth.getName().startsWith("get")) {
					for (Field f : fld) {
						int length = f.getName().length() - 1;
						String substr2 = f.getName().substring(f.getName().length() - length);
						if (meth.getName().contains(substr2)) {
							glbstr1 = f.getName();
						}
					}
					Object methobj = null;
					try {
						methobj = (Object) meth.invoke(itemobj);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (glbstr1 != null && !(glbstr1.startsWith("mer"))) {
						Property property = new Property();
						property.setName(glbstr1);
						property.setValue(ValueType.PRIMITIVE, methobj);
						entity.addProperty(property);
						glbstr1 = null;
					} else {
						if (glbstr1 != null && glbstr1.startsWith("mer")) {
							Property property = new Property();
							property.setName(glbstr1);
							property.setValue(ValueType.ENTITY, methobj);
							entity.addProperty(property);

						}
					}

				}

			}
			entlist.add(entity);
			entity.setId(createId("ItemDetails", 1));
		}

	}

	@Override
	public String getEntitySetName() {
		return ES_MERCHHIER;
	}

	private URI createId(String entitySetName, Object id) {
		try {
			return new URI(entitySetName + "(" + String.valueOf(id) + ")");
		} catch (URISyntaxException e) {
			throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rohitghatol.spring.odata.edm.providers.EntityProvider#
	 * getFullyQualifiedName()
	 */
	@Override
	public FullQualifiedName getFullyQualifiedName() {
		return ET_MERCHHIER_FQN;
	}

}
