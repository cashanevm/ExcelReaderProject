package org.obrii.dp2021.RestJPA.entity.BookBodyResponse;

public class Href {
    String href;

    public Href() {
    }

    public Href(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return href ;
    }
}
