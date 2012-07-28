package net.bodz.art.installer.lic;

import java.io.IOException;

import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;

public class License {

    static String get(String name) {
        URLResource licenseURL = ClassResource.classData(License.class, name);
        licenseURL.setCharset("utf-8");
        try {
            String license = licenseURL.tooling()._for(StreamReading.class).readTextContents();
            return license;
        } catch (IOException e) {
            throw new IllegalArgumentException(PackNLS.getString("License.badLicense") + name);
        }
    }

    public static final String GPLv2 = get("GPLv2");

}
