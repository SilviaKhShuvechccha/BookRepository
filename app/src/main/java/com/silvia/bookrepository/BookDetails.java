package com.silvia.bookrepository;

public class BookDetails {
    String title;
    String author;
    String selfLink;
    String publisher;
    String publishedDate;
    String subTitle;
    String pageCount;
    String averageRating;
    String ratingsCount;
    String language;

    public BookDetails(String title, String author, String selfLink, String publisher, String publishedDate, String subTitle, String pageCount, String averageRating, String ratingsCount, String language) {
        this.title = title;
        this.author = author;
        this.selfLink = selfLink;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.subTitle = subTitle;
        this.pageCount = pageCount;
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getPageCount() {
        return pageCount;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getRatingsCount() {
        return ratingsCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
