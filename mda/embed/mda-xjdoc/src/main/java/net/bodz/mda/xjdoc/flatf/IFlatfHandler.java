package net.bodz.mda.xjdoc.flatf;

public interface IFlatfHandler {

    void sectionStart(String name);

    void sectionEnd(String name);

    void attribute(String name, String text);

}
