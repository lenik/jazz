package net.bodz.bas.html.dom;

public interface IHtmlTagMap {

    IHtmlTag get(String id);

    void add(String id, IHtmlTag tag);

    void remove(String id);

}
