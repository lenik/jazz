package net.bodz.bas.t.predef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.IVarConverterProvider;

public class PredefVarConverters
        implements IVarConverterProvider {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Collection<IVarConverter<?>> getConverters() {
        List<IVarConverter<?>> list = new ArrayList<IVarConverter<?>>();
        for (Class<? extends Predef> type : IndexedTypes.list(Predef.class, false)) {
            PredefMetadata metadata = PredefMetadata.forClass(type);
            Class<?> keyType = metadata.getKeyType();
            PredefVarConverter converter = new PredefVarConverter(type, keyType);
            list.add(converter);
        }
        return list;
    }

}
