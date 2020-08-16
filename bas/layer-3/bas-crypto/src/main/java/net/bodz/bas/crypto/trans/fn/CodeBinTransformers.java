package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.meta.source.FnHelper;

@FnHelper
public class CodeBinTransformers {

    public static final Md5SignTransformer md5 = new Md5SignTransformer("");
    public static final Sha1SignTransformer sha1 = new Sha1SignTransformer("");

    public static Md5SignTransformer md5Sign(String key) {
        return new Md5SignTransformer(key);
    }

    public static PartialMd5Transformer partialMd5(int length, int radix) {
        return new PartialMd5Transformer(length, radix);
    }

    public static Sha1SignTransformer sha1Sign(String key) {
        return new Sha1SignTransformer(key);
    }

}
