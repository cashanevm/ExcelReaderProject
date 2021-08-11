package org.obrii.dp2021.RestJPA.entity.BookBodyResponse;

public class Links {

    Self self;
    Profile profile;
    Search search;


    class Self extends AbstractHref{
        public Self() {
        }
    }
    class Profile extends AbstractHref{
        public Profile() {
        }
    }
    class Search extends AbstractHref{
        public Search() {
        }
    }

    public Links() {
    }

    public Links(Self self, Profile profile, Search search) {
        this.self = self;
        this.profile = profile;
        this.search = search;
    }

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Links{" +
                "self=" + self +
                ", profile=" + profile +
                ", search=" + search +
                '}';
    }
}
