package net.bodz.lily.security.auth;

import net.bodz.bas.crypto.trans.EpochTransient;
import net.bodz.bas.crypto.trans.FlyingAuth;
import net.bodz.bas.crypto.trans.IFlyingSupport;
import net.bodz.bas.crypto.trans.IFlyingTransient;
import net.bodz.bas.crypto.trans.fn.PartialMd5Transformer;

public class FlyingCodes
        implements
            IFlyingSupport {

    public static boolean debug = true;

    static int window = 1_000;
    static IFlyingTransient fTransient = new EpochTransient(window);

    static int md5Len = 6;
    static int md5Radix = 10;
    public static IFlyingTransient core = fTransient.transform(tr.partialMd5(md5Len, md5Radix));

    static int distance = 10 * 60; // 10 minutes
    static int allowAhead = 3 * 60; // 3 minutes

    static PartialMd5Transformer partialMd5 = tr.partialMd5(6, 10);

    public static FlyingAuth smsAuth = new FlyingAuth(core, //
            s -> tr.md5Sign(s).andThen(partialMd5), //
            distance, allowAhead);

    public static FlyingAuth passwordAuth = new FlyingAuth(core, //
            tr::sha1Sign, //
            distance, allowAhead);

    public static FlyingAuth emailAuth = passwordAuth;

    public static int smsTimeout = smsAuth.getTimeout(60_000);

}
