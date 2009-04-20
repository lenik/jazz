package net.bodz.extern.testbeans;

import java.util.HashMap;

public class Environ extends HashMap<String, String> {

    private static final long serialVersionUID = 976559069439914042L;

    public Environ() {
        super(System.getenv());
    }

    public String    publicField         = "a public field";

    protected String protectedField      = "a protected field";

    @SuppressWarnings("unused")
    private String   privateField        = "a private field";
    String           packagePrivateField = "a package privage field";

    String           simpleProperty      = "a simple property";

    public String getSimpleProperty() {
        return simpleProperty;
    }

    public void setSimpleProperty(String simpleProperty) {
        this.simpleProperty = simpleProperty;
    }

    String readOnlyProperty;
    String writeOnlyProperty;

    public String getReadOnlyProperty() {
        return readOnlyProperty;
    }

    public void setWriteOnlyProperty(String writeOnlyProperty) {
        this.writeOnlyProperty = writeOnlyProperty;
    }

    static String staticProperty = "static property";

    public static String getStaticProperty() {
        return staticProperty;
    }

    public static void setStaticProperty(String staticProperty) {
        Environ.staticProperty = staticProperty;
    }

}
