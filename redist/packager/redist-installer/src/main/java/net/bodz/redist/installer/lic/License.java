package net.bodz.redist.installer.lic;

import java.io.IOException;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;

public class License
        implements II18nCapable {

    static String get(String name) {
        URLResource licenseURL = ClassResource.classData(License.class, name);
        licenseURL.setCharset("utf-8");
        try {
            String license = licenseURL.tooling()._for(StreamReading.class).readTextContents();
            return license;
        } catch (IOException e) {
            throw new IllegalArgumentException(tr._("No such license: ") + name);
        }
    }

    public static final String GPLv2 = get("GPLv2");

}
