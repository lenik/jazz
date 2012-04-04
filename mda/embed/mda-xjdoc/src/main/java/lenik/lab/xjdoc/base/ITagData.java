package lenik.lab.xjdoc.base;

public interface ITagData {

    void parseAnnotation(String text);

    void readObject(String key, String encoded);

    void encode();

}
