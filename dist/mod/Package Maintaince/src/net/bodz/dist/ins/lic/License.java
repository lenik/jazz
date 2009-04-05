package net.bodz.dist.ins.lic;

import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;

public class License {

    static String get(String name) {
        URL licenseURL = Files.classData(License.class, name);
        try {
            String license = Files.readAll(licenseURL, "utf-8");
            return license;
        } catch (IOException e) {
            throw new IllegalArgumentException("No such license: " + name);
        }
    }

    public static final String GPLv2 = get("GPLv2");

}
