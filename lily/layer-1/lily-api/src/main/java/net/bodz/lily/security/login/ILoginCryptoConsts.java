package net.bodz.lily.security.login;

import net.bodz.bas.crypto.trans.IFlyingSupport;
import net.bodz.bas.crypto.trans.fn.PartialMd5Transformer;
import net.bodz.bas.sugar.IConstants;

public interface ILoginCryptoConsts
        extends IConstants, IFlyingSupport {

    PartialMd5Transformer SHORT_TEXT = tr.partialMd5(6, 10);

}
