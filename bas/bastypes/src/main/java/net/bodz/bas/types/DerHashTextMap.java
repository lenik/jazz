package net.bodz.bas.types;

import java.util.Map;

import net.bodz.bas.lang.a.Typedef;
import net.bodz.bas.types.der.DerHashMap;

@Typedef
public class DerHashTextMap<T> extends DerHashMap<String, T> implements TextMap<T> {

    private static final long serialVersionUID = 3740317197044614640L;

    public DerHashTextMap(Map<String, T> pMap) {
        super(pMap);
    }

}
