package net.bodz.bas.t.specmap;

public class LayerKeyValue<val_t>
        implements
            ILayerKeyValue<val_t> {

    SpecLayer layer;
    Object layerKey;
    val_t value;

    public LayerKeyValue(SpecLayer layer, Object layerKey, val_t value) {
        this.layer = layer;
        this.layerKey = layerKey;
        this.value = value;
    }

    @Override
    public SpecLayer getLayer() {
        return layer;
    }

    @Override
    public Object getLayerKey() {
        return layerKey;
    }

    @Override
    public val_t getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s<%s>: %s", layer, layerKey, value);
    }

    public static <V> LayerKeyValue<V> top(Object key, V value) {
        return new LayerKeyValue<>(SpecLayer.TOP, key, value);
    }

    public static <V> LayerKeyValue<V> range(IRange<?> rangeKey, V value) {
        return new LayerKeyValue<>(SpecLayer.RANGE, rangeKey, value);
    }

    public static <V> LayerKeyValue<V> domain(String domain, V value) {
        return new LayerKeyValue<>(SpecLayer.DOMAIN, domain, value);
    }

    public static <V> LayerKeyValue<V> _default(V value) {
        return new LayerKeyValue<>(SpecLayer.DEFAULT, null, value);
    }

}
