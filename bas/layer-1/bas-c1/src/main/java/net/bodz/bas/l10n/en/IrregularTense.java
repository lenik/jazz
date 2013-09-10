package net.bodz.bas.l10n.en;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import net.bodz.bas.c.java.util.UTF8ResourceBundle;

class IrregularTense {

    static final Map<String, String> pastTense = new HashMap<String, String>();
    static final Map<String, String> perfectTense = new HashMap<String, String>();

    static void load() {
        ResourceBundle bundle = UTF8ResourceBundle.getBundle(IrregularTense.class.getName());
        for (String name : bundle.keySet()) {
            String value = bundle.getString(name);

            int colon = value.indexOf(':');
            String past = value;
            String perfect;
            if (colon == -1)
                perfect = past;
            else {
                perfect = value.substring(colon + 1);
                past = value.substring(0, colon);
            }
            pastTense.put(name, past);
            perfectTense.put(name, perfect);
        }
    }

    static {
        load();
    }

}
