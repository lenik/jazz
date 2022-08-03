package net.bodz.bas.t.catalog;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class ListMap_JX<K, V extends IJsonForm>
        extends ListMap<K, V>
        implements
            IJsonForm {

    Class<K> keyType;
    Supplier<V> factory;

    public ListMap_JX(Class<K> keyType, Supplier<V> factory) {
        this(keyType, factory, SortOrder.NONE);
    }

    public ListMap_JX(Class<K> keyType, Supplier<V> factory, SortOrder order) {
        super(order);
        if (keyType == null)
            throw new NullPointerException("keyType");
        if (factory == null)
            throw new NullPointerException("factory");
        this.keyType = keyType;
        this.factory = factory;
    }

    protected K parseKey(String s)
            throws ParseException {
        IParser<K> parser = Typers.getTyper(keyType, IParser.class);
        return parser.parse(s);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        for (String key : o.keySet()) {
            K k1 = parseKey(key);
            List<V> list = getOrCreate(k1);
            JsonArray array = o.getJsonArray(key);
            int n = array.length();
            for (int i = 0; i < n; i++) {
                JsonObject o2 = array.getJsonObject(i);
                V item = factory.get();
                item.readObject(o2);
                list.add(item);
            }
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException, FormatException {
        for (K k1 : keySet()) {
            out.key(k1.toString());
            List<V> list = get(k1);
            out.array();
            for (V item : list) {
                item.writeObjectBoxed(out);
            }
            out.endArray();
        }
    }

}
