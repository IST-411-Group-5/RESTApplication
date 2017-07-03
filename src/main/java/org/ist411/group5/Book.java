package org.ist411.group5;


/**
 * REST Web Service
 *
 * @author Eric Ruppert, Miao Yu, Susan Tabassum, Erik Galloway
 */


public class Book {
    private String title;
    private String author;
    private String ISBN;
    public Book(String ttl, String auth, String isbn){
        title = ttl;
        author = auth;
        ISBN = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
