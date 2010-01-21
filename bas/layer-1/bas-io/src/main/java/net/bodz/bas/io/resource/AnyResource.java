package net.bodz.bas.io.resource;

import java.io.IOException;

public class AnyResource {

    public static IStreamInputSource wantInput(Object any)
            throws IOException {
        return null;
    }

    public static IStreamOutputTarget wantOutput(Object any)
            throws IOException {
        return null;
    }

    public static <T extends IStreamInputSource & IStreamOutputTarget> T wantStreamAccess(Object any) {
        return null;
    }

    public static IRandomInputSource wantRandomInput(Object any)
            throws IOException {
        return null;
    }

    public static IRandomOutputTarget wantRandomOutput(Object any)
            throws IOException {
        return null;
    }

    public static <T extends IRandomInputSource & IRandomOutputTarget> T wantRandomAccess(Object any) {
        return null;
    }

}
