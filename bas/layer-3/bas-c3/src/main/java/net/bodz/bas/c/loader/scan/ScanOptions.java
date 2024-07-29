package net.bodz.bas.c.loader.scan;

public class ScanOptions {

    public static IScanOptions classOnly(IScanOptions options) {
        return new DecoratedScanOptions(options) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean acceptFileName(String name) {
                if (! name.endsWith(".class"))
                    return false;
                return super.acceptFileName(name);
            }
        };
    }

}
