package net.bodz.bas.types;

import java.util.TreeMap;

import net.bodz.bas.types.der.DerTreeMap;

public class DerTreeTextMap<T> extends DerTreeMap<String, T> implements
        TextMap<T> {

    private static final long serialVersionUID = 3740317197044614640L;

    public DerTreeTextMap(TreeMap<String, T> pMap) {
        super(pMap);
    }

}
