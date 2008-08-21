package net.bodz.bas.mod.plugins;

public interface Plugin {

    // static methods

    String getDescription();

    // instance methods

    /**
     * Parameters is defined in System.properties.
     */
    @Deprecated
    void initialize();

}
