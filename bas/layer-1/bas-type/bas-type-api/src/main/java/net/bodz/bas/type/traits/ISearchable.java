package net.bodz.bas.type.traits;

import java.util.Iterator;

public interface ISearchable {

    Iterator<?> search(String queryString);

}
