package com.silvia.bookrepository;

public class Book {
    public String Id;
    public String BookTitle;
    public String AuthorName;
    public String SelfLink;
    public String PublishedDate;
    public String PageCount;
    public String Language;
    public String Description;

    public Book(String bookTitle, String authorName, String selfLink) {
        BookTitle = bookTitle;
        AuthorName = authorName;
        SelfLink = selfLink;
    }
    public Book()
    {

    }
    public Book(String b)
    {
        Id = b;
    }

    public Book(String id, String bookTitle, String authorName, String selfLink, String publishedDate, String pageCount, String language, String description) {
        Id = id;
        BookTitle = bookTitle;
        AuthorName = authorName;
        SelfLink = selfLink;
        PublishedDate = publishedDate;
        PageCount = pageCount;
        Language = language;
        Description = description;
    }

    public Book(String bookTitle, String authorName, String selfLink, String publishedDate, String pageCount, String language, String description) {
        BookTitle = bookTitle;
        AuthorName = authorName;
        SelfLink = selfLink;
        PublishedDate = publishedDate;
        PageCount = pageCount;
        Language = language;
        Description = description;
    }

    public String getId() {
        return Id;
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

    public String getPublishedDate() {
        return PublishedDate;
    }

    public String getPageCount() {
        return PageCount;
    }

    public String getLanguage() {
        return Language;
    }

    public String getDescription() {
        return Description;
    }

    public void setId(String id) {
        Id = id;
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

    public void setPublishedDate(String publishedDate) {
        PublishedDate = publishedDate;
    }

    public void setPageCount(String pageCount) {
        PageCount = pageCount;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
