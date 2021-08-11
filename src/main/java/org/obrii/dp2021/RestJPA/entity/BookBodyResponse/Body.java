package org.obrii.dp2021.RestJPA.entity.BookBodyResponse;

import org.obrii.dp2021.RestJPA.entity.Book;

import java.util.List;

public class Body {
    Embedded _embedded;
    Links _links;
    Page page;
    public Body() {
    }

    public Body(Embedded _embedded, Links _links, Page page) {
        this._embedded = _embedded;
        this._links = _links;
        this.page = page;
    }

    public Embedded get_embedded() {
        return _embedded;
    }

    public void set_embedded(Embedded _embedded) {
        this._embedded = _embedded;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Book> getAll(){
        return _embedded.getBook();
    };

    @Override
    public String toString() {
        return "Body{" +
                "_embedded=" + _embedded +
                ", _links=" + _links +
                ", page=" + page +
                '}';
    }
}
