package net.bodz.bas.type;

import java.util.Iterator;

public interface ISearchable {

    Iterator<?> search(String queryString);

}
