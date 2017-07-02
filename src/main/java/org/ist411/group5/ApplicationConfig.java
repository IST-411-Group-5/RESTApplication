package org.ist411.group5;

import java.util.Set;
import javax.ws.rs.core.Application;


/**
 *
 * @author Eric Ruppert
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.ist411.group5.BooksResource.class);
    }
    
}
