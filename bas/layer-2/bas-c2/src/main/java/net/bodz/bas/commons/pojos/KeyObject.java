package net.bodz.bas.commons.pojos;

import java.util.HashMap;
import java.util.Map;

public class KeyObject {

    public KeyObject() {
    }

    public KeyObject(String name, boolean unique) {
        if (unique) {
            if (namedKeys.containsKey(name))
                throw new IllegalArgumentException("Key " + name + "is existed");  
        }
        namedKeys.put(this, name);
    }

    public KeyObject(String name) {
        this(name, false);
    }

    @Override
    public boolean equals(Object object) {
        return this == object;
    }

    @Override
    public String toString() {
        String name = namedKeys.get(this);
        if (name == null)
            name = "" + hashCode(); 
        return "<Key:" + name + ">";  
    }

    static Map<KeyObject, String> namedKeys = new HashMap<KeyObject, String>();

}
