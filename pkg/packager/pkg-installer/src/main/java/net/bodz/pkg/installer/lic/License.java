package net.bodz.pkg.installer.lic;

import java.io.IOException;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;

public class License
        implements II18nCapable {

    static String get(String name) {
        URLResource licenseURL = ClassResource.getData(License.class, name);
        licenseURL.setCharset("utf-8");
        try {
            String license = licenseURL.to(StreamReading.class).readString();
            return license;
        } catch (IOException e) {
            throw new IllegalArgumentException(tr._("No such license: ") + name);
        }
    }

    public static final String GPLv2 = get("GPLv2");

}
