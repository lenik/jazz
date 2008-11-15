package net.bodz.bas.loader;

public interface LoadConfig {

    ClassLoader getLoader(ClassLoader parent) throws LoadException;

    /**
     * Not used.
     */
    void load(int stageFrom, int stageTo) throws LoadException;

    /**
     * Not used.
     */
    void loadAll() throws LoadException;

}
