package user.book;

import java.util.ArrayList;
import java.util.List;

public class Org {

    String name;

    List<Author> authors = new ArrayList<>();

    public Org(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

}
