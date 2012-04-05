package net.bodz.mda.xjdoc.meta;

public interface ITagData {

    void parseAnnotation(String text);

    void readObject(String key, String encoded);

    void encode();

}
