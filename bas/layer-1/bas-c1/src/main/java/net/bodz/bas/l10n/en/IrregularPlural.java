package net.bodz.bas.l10n.en;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import net.bodz.bas.c.java.util.UTF8ResourceBundle;

class IrregularPlural {

    static final Map<String, String> pluralForm = new HashMap<String, String>();
    static final Map<String, String> singularForm = new HashMap<String, String>();

    static void load() {
        ResourceBundle bundle = UTF8ResourceBundle.getBundle(IrregularPlural.class.getName());
        for (String name : bundle.keySet()) {
            String value = bundle.getString(name);

            pluralForm.put(name, value);
            singularForm.put(value, name);
        }
    }

    static {
        load();
    }

}
