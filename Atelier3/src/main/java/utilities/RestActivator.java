package utilities;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import resources.GestionEmploye;


@ApplicationPath("/api")
public class RestActivator extends Application {
	@Override
	public Set<Class<?>> getClasses() {

	Set<Class<?>> resources = new HashSet();
	resources.add(GestionEmploye.class);
	// Available at localhost:port/swagger.json
	resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	return resources;

	}
public RestActivator() {
	super();
	
	BeanConfig beanConfig = new BeanConfig();
	
	beanConfig.setVersion("1.0.0");
	beanConfig.setSchemes(new String[]{"http"});
	beanConfig.setHost("localhost:8088");
	beanConfig.setBasePath("GestionEmploye/api");
	beanConfig.setResourcePackage("resources");
	beanConfig.setScan(true);
}
	
}

