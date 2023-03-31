package net.bodz.lily;

import java.util.List;

import net.bodz.uni.shelj.codegen.java.service.TypeCollectorApp;

public class LilyUberTypeCollector
        extends TypeCollectorApp {

    @Override
    protected void buildPackageList(List<String> packages) {
        packages.add("net.bodz.lily");
        packages.add("net.bodz.violet");
    }

    public static void main(String[] args)
            throws Exception {
        new LilyUberTypeCollector().execute(args);
    }

}
