package net.bodz.bas.typeinfo;

import java.util.Iterator;

public interface Searchable {

    Iterator<?> search(String queryString);

}
