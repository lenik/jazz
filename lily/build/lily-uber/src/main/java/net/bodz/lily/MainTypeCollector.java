package net.bodz.lily;

import java.util.List;

import net.bodz.bas.shell.util.TypeCollectorApp;

public class MainTypeCollector
        extends TypeCollectorApp {

    @Override
    protected void buildPackageList(List<String> packages) {
        super.buildPackageList(packages);
        packages.add("net.bodz.violet");
    }

    public static void main(String[] args)
            throws Exception {
        new MainTypeCollector().execute(args);
    }

}