package user.book;

import java.util.List;

import net.bodz.bas.repr.path.PathToken;

public class Book {

    /**
     * @http.header Comment: page lists.
     */
    // Default: @PathToken("page")
    List<Page> pages;

    @PathToken("page:alt")
    List<Page> altPageList;

    Author author;

}
