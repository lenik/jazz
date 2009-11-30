package net.bodz.bas.aspect.typeinfo;

import java.util.Iterator;

public interface Searchable {

    Iterator<?> search(String queryString);

}
