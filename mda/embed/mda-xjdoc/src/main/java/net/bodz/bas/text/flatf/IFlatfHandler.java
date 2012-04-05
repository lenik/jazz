package net.bodz.bas.text.flatf;

public interface IFlatfHandler {

    void sectionStart(String name);

    void sectionEnd(String name);

    void attribute(String name, String text);

}
