package net.bodz.bas.t.specmap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractSpecMapBase<key_t, val_t>
        implements
            ISpecMap<key_t, val_t>,
            IJsonForm {

    boolean hasDefaultVal;
    val_t defaultVal;

    protected final boolean nullable;
    protected final boolean allowOverlappedRange;

    public AbstractSpecMapBase() {
        nullable = isNullable();
        allowOverlappedRange = isAllowOverlappedRange();
    }

    public boolean isNullable() {
        return true;
    }

    public boolean isAllowOverlappedRange() {
        return false;
    }

    @Override
    public val_t find(key_t key) {
        val_t val = getTop(key);
        if (val != null)
            return val;
        else if (nullable && containsTop(key))
            return null;
        return _find(key);
    }

    protected val_t _find(key_t key) {
        return defaultVal;
    }

    @Override
    public final List<ILayerKeyValue<val_t>> findAll(key_t key) {
        List<ILayerKeyValue<val_t>> list = new ArrayList<>();
        findAll(key, list);
        return list;
    }

    protected final void findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
        if (containsTop(key))
            list.add(LayerKeyValue.top(key, getTop(key)));
        _findAll(key, list);
    }

    protected void _findAll(key_t key, List<ILayerKeyValue<val_t>> list) {
        if (hasDefaultVal)
            list.add(LayerKeyValue._default(defaultVal));
    }

    @Override
    public final SpecKeyLocation whichKey(key_t key) {
        if (containsTop(key))
            return SpecKeyLocation.top(key);
        return _whichKey(key);
    }

    protected SpecKeyLocation _whichKey(key_t key) {
        if (hasDefaultVal)
            return SpecKeyLocation._default();
        return null;
    }

    @Override
    public final SpecLayer whichLayer(key_t key) {
        SpecKeyLocation keyLoc = whichKey(key);
        if (keyLoc == null)
            return null;
        else
            return keyLoc.layer;
    }

    @Override
    public final ILayerKeyValue<val_t> removeFromLayer(SpecLayer layer, Object key) {
        if (layer == SpecLayer.TOP) {
            @SuppressWarnings("unchecked")
            key_t topKey = (key_t) key;
            val_t val = removeTop(topKey);
            return new LayerKeyValue<>(layer, key, val);
        }
        if (layer == SpecLayer.DEFAULT) {
            hasDefaultVal = false;
            val_t last = defaultVal;
            defaultVal = null;
            return new LayerKeyValue<>(layer, null, last);
        }
        return _removeFromLayer(layer, key);
    }

    protected ILayerKeyValue<val_t> _removeFromLayer(SpecLayer layer, Object key) {
        return null;
    }

    @Override
    public final ILayerKeyValue<val_t> removeFromAnyLayer(key_t key) {
        if (containsTop(key)) {
            val_t val = removeTop(key);
            return LayerKeyValue.top(key, val);
        }
        return _removeFromAnyLayer(key);
    }

    protected ILayerKeyValue<val_t> _removeFromAnyLayer(key_t key) {
        if (hasDefaultVal) {
            hasDefaultVal = false;
            val_t last = defaultVal;
            defaultVal = null;
            return LayerKeyValue._default(last);
        }
        return null;
    }

    @Override
    public boolean hasDefault() {
        return hasDefaultVal;
    }

    @Override
    public val_t getDefault() {
        if (hasDefaultVal)
            return defaultVal;
        else
            return null;
    }

    @Override
    public val_t putDefault(val_t value) {
        val_t last = this.defaultVal;
        this.defaultVal = value;
        this.hasDefaultVal = true;
        return last;
    }

    @Override
    public val_t removeDefault() {
        hasDefaultVal = false;
        val_t last = defaultVal;
        defaultVal = null;
        return last;
    }

    @Override
    public void accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
        Set<key_t> topKeys = topKeySet();
        if (!topKeys.isEmpty()) {
            if (visitor.beginTops()) {
                for (key_t key : topKeys) {
                    val_t val = getTop(key);
                    if (!visitor.visitTop(key, val))
                        break;
                    if (visitor.beginValue(SpecLayer.TOP, key)) {
                        visitor.visitValue(val);
                        visitor.endValue();
                    }
                }
                visitor.endTops();
            }
        }

        _accept(visitor);

        if (hasDefaultVal) {
            visitor.visitDefault(defaultVal);
            if (visitor.beginValue(SpecLayer.DEFAULT, null)) {
                visitor.visitValue(defaultVal);
                visitor.endValue();
            }
        }
    }

    protected void _accept(ISpecMapVisitor<? super key_t, ? super val_t> visitor) {
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        SpecMapDumper<key_t, val_t> dumper = new SpecMapDumper<>(buf.indented());
        accept(dumper);
        return buf.toString();
    }

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        // this.accept(new SpecMapJsonDumper<>(out));
        if (hasDefaultVal)
            out.entry("defaultValue", defaultVal);
        out.entry("nullable", nullable);
        out.entry("allowOverlappedRange", allowOverlappedRange);
    }

}
