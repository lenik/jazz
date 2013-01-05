package net.bodz.bas.l10n.en;

import java.util.Locale;
import java.util.ResourceBundle;

import net.bodz.bas.c.java.util.UTF8ResourceBundle;

public class IrregularPlural {

    public interface Handler {
        void handle(String name, String value);
    }

    public static void process(Locale locale, Handler handler) {
        String baseName = IrregularPlural.class.getName();
        ResourceBundle bundle = UTF8ResourceBundle.getBundle(baseName, locale);
        for (String name : bundle.keySet()) {
            String value = bundle.getString(name);
            handler.handle(name, value);
        }
    }

}
