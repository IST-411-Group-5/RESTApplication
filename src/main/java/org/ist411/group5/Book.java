package org.ist411.group5;

/**
 *
 * @author Susan
 */
public class Book {
    private String title;
    private String author;
    private long ISBN;
    public Book(String ttl, String auth, long isbn){
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
    public long getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }
}
