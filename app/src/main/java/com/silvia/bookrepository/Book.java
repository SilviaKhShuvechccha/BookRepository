package com.silvia.bookrepository;

public class Book {
    public String BookTitle;
    public String AuthorName;
    public String SelfLink;

    public Book(String bookTitle, String authorName, String selfLink) {
        BookTitle = bookTitle;
        AuthorName = authorName;
        SelfLink = selfLink;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getSelfLink() {
        return SelfLink;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public void setSelfLink(String selfLink) {
        SelfLink = selfLink;
    }
}
