package net.bodz.bas.t.specmap;

public class SpecLayer {

    public static SpecLayer TOP = new SpecLayer("top");

    public static SpecLayer RANGE = new SpecLayer("range");

    public static SpecLayer DOMAIN = new SpecLayer("domain");

    public static SpecLayer DEFAULT = new SpecLayer("default");

    private final String name;

    public SpecLayer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
