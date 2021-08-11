package org.obrii.dp2021.RestJPA.entity.BookBodyResponse;

import org.obrii.dp2021.RestJPA.entity.Book;

import java.util.List;

public class Embedded {
    List<Book> book;

    public Embedded() {
    }

    public Embedded(List<Book> book) {
        this.book = book;
    }
    public void setBooks(List<Book> book) {
        this.book = book;
    }
    public List<Book> getBook() {
        return book;
    }

    @Override
    public String toString() {
        return  book.toString() ;
    }
}
