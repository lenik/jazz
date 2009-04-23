package net.bodz.dist.ins.lic;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.dist.nls.PackNLS;

public class License {

    static String get(String name) {
        URL licenseURL = Files.classData(License.class, name);
        try {
            String license = Files.readAll(licenseURL, "utf-8"); //$NON-NLS-1$
            return license;
        } catch (IOException e) {
            throw new IllegalArgumentException(PackNLS.getString("License.badLicense") + name); //$NON-NLS-1$
        }
    }

    public static final String GPLv2 = get("GPLv2"); //$NON-NLS-1$

}
