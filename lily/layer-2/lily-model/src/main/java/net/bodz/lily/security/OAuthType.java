package net.bodz.lily.security;

import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;

public class OAuthType
        extends Predef<OAuthType, String> {

    private static final long serialVersionUID = 1L;

    public static final PredefMetadata<OAuthType, String> meta = PredefMetadata.forClass(OAuthType.class);

    public OAuthType(String key, String name) {
        super(key, name, meta);
    }

    public static final String K_QQ = "QQ";
    public static final String K_WECHAT = "WeChat";

    /**
     * QQ
     */
    public static final OAuthType QQ = new OAuthType(K_QQ, "QQ");
    
    /**
     * WeChat
     */
    public static final OAuthType WECHAT = new OAuthType(K_WECHAT, "WECHAT");

}
