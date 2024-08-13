package net.bodz.lily;

import java.util.List;

import net.bodz.uni.shelj.codegen.java.service.TypeCollectorApp;

public class LilyApiTypeCollector
        extends TypeCollectorApp {

    @Override
    protected void buildPackageList(List<String> packages) {
        packages.add("net.bodz.lily");
    }

    public static void main(String[] args)
            throws Exception {
        new LilyApiTypeCollector().execute(args);
    }

}
