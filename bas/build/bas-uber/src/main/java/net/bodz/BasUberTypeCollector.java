package net.bodz;

import java.util.List;

import net.bodz.bas.shell.util.TypeCollectorApp;

public class BasUberTypeCollector
        extends TypeCollectorApp {

    @Override
    protected void buildPackageList(List<String> packages) {
        packages.add("net.bodz");
        packages.add("user");
    }

    public static void main(String[] args)
            throws Exception {
        new BasUberTypeCollector().execute(args);
    }

}
