package lenik.lab.xjdoc.flatf;

public interface IFlatfHandler {

    void sectionStart(String name);

    void sectionEnd(String name);

    void attribute(String name, String text);

}
