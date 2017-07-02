package org.ist411.group5;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.*;
/**
 * REST Web Service
 *
 * @author Eric Ruppert
 */
@Path("books")
public class BooksResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookaceResource
     */
    public BooksResource() {
    }

    /**
     * Retrieves representation of an instance of org.ist411.group5.BooksResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return "<body>a book</body>";
    }

    /**
     * PUT method for updating or creating an instance of BooksResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    /*
     *POST method writes an instance of BooksResource to the server
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void postBook() {
        ArrayList<Book> bookList = new ArrayList();
        Book book1 = new Book("Brave New World", "Aldous Huxley", 9780060850524L);
        Book book2 = new Book("Nineteen Eighty-Four", "George Orwell", 9780141182957L);
        Book book3 = new Book("Lord of the Flies", "William Golding", 9780807218181L);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Book book : bookList){
            arrayBuilder.add(
                    Json.createObjectBuilder()
                    .add("Title", book.getTitle())
                    .add("Author", book.getAuthor())
                    .add("ISBN", book.getISBN())
            );
        }
        JsonArray bookArray = arrayBuilder.build();
        try {
            OutputStream output = new FileOutputStream("Books.json");
            JsonWriter writer = Json.createWriter(output);
            writer.writeArray(bookArray);
            output.close();
            writer.close();
        } catch(IOException e){
            System.out.println("File not found!");
        }
    }
}
