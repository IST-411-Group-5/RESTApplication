package org.ist411.group5;

import javax.json.*;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Eric Ruppert
 */
@Path("books")
public class Books {
    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBooks(){
        return "a book";
        
    }
   
}
