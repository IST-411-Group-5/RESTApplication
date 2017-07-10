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
import java.io.*;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
/**
 * REST Web Service
 *
 * @author Eric Ruppert, Miao Yu, Susan Tabassum, Erik Galloway
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
        String html = "<html><body>";
        try {
            File file = new File("books.json");
            if (file.exists()){
                FileReader reader = new FileReader(file);
                JsonArray data = Json.createReader(reader).readArray();

                for (int i = 0; i < data.size(); i++) {
                    JsonObject bookData = data.getJsonObject(i);
                    html += "<ul><li>Title: " +  bookData.getJsonString("Title").getString() + "</li>";
                    html += "<li>Author: " +  bookData.getJsonString("Author").getString() + "</li>";
                    html += "<li>ISBN: " +  bookData.getJsonString("ISBN").getString() + "</li></ul>";

                }
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return html + "</body></html>";
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
     *Uses the form provided in index.html
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String postBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("isbn") String isbn) {
        
        Book newBook = new Book(title, author, isbn);
        JsonArrayBuilder builder = Json.createArrayBuilder();
        JsonArray json = null;
        try {
            File file = new File("books.json");
            if (file.exists()){
                FileReader reader = new FileReader(file);
                JsonArray data = Json.createReader(reader).readArray();

                for (int i = 0; i < data.size(); i++) {
                    JsonObject bookData = data.getJsonObject(i);


                    Book book = new Book(
                            bookData.getJsonString("Title").getString(), 
                            bookData.getJsonString("Author").getString(), 
                            bookData.getJsonString("ISBN").getString()
                    );

                     builder.add(
                        Json.createObjectBuilder()
                        .add("Title", book.getTitle())
                        .add("Author", book.getAuthor())
                        .add("ISBN", book.getISBN()));

                }
            }
            builder.add(
                    Json.createObjectBuilder()
                    .add("Title", newBook.getTitle())
                    .add("Author", newBook.getAuthor())
                    .add("ISBN", newBook.getISBN()));
            
            json = builder.build();
            FileWriter fileWriter = new FileWriter(file);
            JsonWriter writer = Json.createWriter(fileWriter);
            writer.writeArray(json);
            writer.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "<body>Success. Click <a href=\"./books\">here</a> to see all books.</body>";
        

    }
    
    /*
    Update a book with the given title and author based on the provided isbn number.
    Fields are provided in the header of the PUT request
    
    */
    @PUT
    @Produces(MediaType.TEXT_HTML)
    public String updateBook(@HeaderParam ("title") String title, @HeaderParam("author") String author, @HeaderParam("isbn") String isbn) {
        
        Book newBook = new Book(title, author, isbn);
        JsonArrayBuilder builder = Json.createArrayBuilder();
        JsonArray json = null;
        try {
            File file = new File("books.json");
            if (file.exists()){
                FileReader reader = new FileReader(file);
                JsonArray data = Json.createReader(reader).readArray();

                for (int i = 0; i < data.size(); i++) {
                    JsonObject bookData = data.getJsonObject(i);
                    Book book = null;
                    if (bookData.getJsonString("ISBN").getString().equals(isbn)){
                        book = new Book(title, author, isbn);
                        newBook = null;
                    }
                    else{
                        book = new Book(
                                bookData.getJsonString("Title").getString(), 
                                bookData.getJsonString("Author").getString(), 
                                bookData.getJsonString("ISBN").getString()
                        );
                    }
                    
                    builder.add(
                        Json.createObjectBuilder()
                                .add("Title", book.getTitle())
                                .add("Author", book.getAuthor())
                                .add("ISBN", book.getISBN()));

                }
            }
            if (newBook != null){
                return "<body>Book not found</body>";
            }
            json = builder.build();
            FileWriter fileWriter = new FileWriter(file);
            JsonWriter writer = Json.createWriter(fileWriter);
            writer.writeArray(json);
            writer.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "<body>Success. Click <a href=\"./books\">here</a> to see all books.</body>";
        

    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteBook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("isbn") String isbn) {
        
        Book newBook = new Book(title, author, isbn);
        JsonArrayBuilder builder = Json.createArrayBuilder();
        JsonArray json = null;
        boolean found = false;
        try {
            File file = new File("books.json");
            if (file.exists()){
                FileReader reader = new FileReader(file);
                JsonArray data = Json.createReader(reader).readArray();
                
                for (int i = 0; i < data.size(); i++) {
                    JsonObject bookData = data.getJsonObject(i);
                    if (!bookData.getJsonString("ISBN").getString().equals(isbn)){
                        builder.add(
                        Json.createObjectBuilder()
                                .add("Title", bookData.getJsonString("Title").getString())
                                .add("Author", bookData.getJsonString("Author").getString())
                                .add("ISBN", bookData.getJsonString("ISBN").getString()));
                    } else {
                        found = true;
                    }
                }
            }
            if (!found){
                return "<body>Book not found</body>";
            } 
            json = builder.build();
            FileWriter fileWriter = new FileWriter(file);
            JsonWriter writer = Json.createWriter(fileWriter);
            writer.writeArray(json);
            writer.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        
        return "<body>Success. Click <a href='localhost:8080/RESTApplication/webresources/books'>here</a> to see all books.</body>";
        

    }
}
