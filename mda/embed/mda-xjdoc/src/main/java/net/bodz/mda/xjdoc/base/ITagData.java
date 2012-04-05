package net.bodz.mda.xjdoc.base;

public interface ITagData {

    void parseAnnotation(String text);

    void readObject(String key, String encoded);

    void encode();

}
