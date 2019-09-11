package user.book;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.repr.path.PathToken;

public class Book {

    String title;

    /**
     * @http.header Comment: page lists.
     */
    // Default: @PathToken("page")
    List<Page> pages = new ArrayList<Page>();

    @PathToken("page:alt")
    List<Page> altPageList = new ArrayList<Page>();

    Author author;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public List<Page> getAltPageList() {
        return altPageList;
    }

    public void setAltPageList(List<Page> altPageList) {
        this.altPageList = altPageList;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
