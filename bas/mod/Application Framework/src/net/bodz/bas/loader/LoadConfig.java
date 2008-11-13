package net.bodz.bas.loader;

public interface LoadConfig {

    ClassLoader getLoader(ClassLoader parent);

    /**
     * Not used.
     */
    void load(int stageFrom, int stageTo);

    /**
     * Not used.
     */
    void loadAll();

}
