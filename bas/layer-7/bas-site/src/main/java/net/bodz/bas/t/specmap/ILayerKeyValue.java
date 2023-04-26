package net.bodz.bas.t.specmap;

public interface ILayerKeyValue<val_t> {

    SpecLayer getLayer();

    Object getLayerKey();

    val_t getValue();

}
