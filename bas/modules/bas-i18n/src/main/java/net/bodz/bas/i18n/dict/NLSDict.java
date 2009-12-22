package net.bodz.bas.i18n.dict;

public interface NLSDict {

    String getTitle();

    boolean contains(String key);

    Object get(String key);

    Object get(String key, Object def);

    String getString(String key);

    String getString(String key, String def);

    String format(String key, Object... args);

    String tr(String name);

}
