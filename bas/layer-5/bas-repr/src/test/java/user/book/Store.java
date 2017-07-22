package user.book;

import java.util.ArrayList;
import java.util.List;

public class Store {

    String logo;

    List<Book> books = new ArrayList<>();

    public Store(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
