package org.obrii.dp2021.RestJPA.entity.BookBodyResponse;

public abstract class AbstractHref {
    Href href;
    public AbstractHref() {
    }

    public AbstractHref(Href href) {
        this.href = href;
    }

    public Href getHref() {
        return href;
    }

    public void setHref(Href href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "{" +
                "href=" + href +
                '}';
    }
}
