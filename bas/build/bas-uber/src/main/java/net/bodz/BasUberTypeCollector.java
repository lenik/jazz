package net.bodz;

import java.util.List;

import net.bodz.uni.shelj.codegen.java.service.TypeCollectorApp;

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
