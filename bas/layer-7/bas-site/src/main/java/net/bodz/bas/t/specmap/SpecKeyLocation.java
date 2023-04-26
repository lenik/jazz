package net.bodz.bas.t.specmap;

public class SpecKeyLocation {

    public SpecLayer layer;
    public Object layerKey;

    private SpecKeyLocation(SpecLayer layer, Object layerKey) {
        this.layer = layer;
        this.layerKey = layerKey;
    }

    public static SpecKeyLocation top(Object key) {
        return new SpecKeyLocation(SpecLayer.TOP, key);
    }

    public static SpecKeyLocation range(IRange<?> rangeKey) {
        return new SpecKeyLocation(SpecLayer.RANGE, rangeKey);
    }

    public static SpecKeyLocation domain(String domain) {
        return new SpecKeyLocation(SpecLayer.DOMAIN, domain);
    }

    public static SpecKeyLocation _default() {
        return new SpecKeyLocation(SpecLayer.DEFAULT, null);
    }

}
