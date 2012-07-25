package net.bodz.bas.util;

public interface IPlugin {

    // static methods

    String getDescription();

    // instance methods

    /**
     * Parameters is defined in System.properties.
     */
    @Deprecated
    void initialize();

}
