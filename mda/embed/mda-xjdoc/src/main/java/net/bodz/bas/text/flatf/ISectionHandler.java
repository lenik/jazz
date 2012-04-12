package net.bodz.bas.text.flatf;

public interface ISectionHandler {

    boolean pi(String command, String data);

    void sectionBegin(String name);

    void sectionEnd(String name);

    void attribute(String name, String string);

}
