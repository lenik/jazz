package net.bodz;

import java.util.List;

import net.bodz.bas.codegen.java.TypeCollectorApp;

public class BasUberTestTypeCollector
        extends TypeCollectorApp {

    @Override
    protected void buildPackageList(List<String> packages) {
        packages.add("net.bodz");
        packages.add("user");
    }

    public static void main(String[] args)
            throws Exception {
        new BasUberTestTypeCollector().execute(args);
    }

}
