package net.bodz.bas.http.model;

import java.util.Map;

public interface IHttpHeadData {

    String HTTP_CONTENT_TYPE = "content-type";
    String HTTP_DEFAULT_STYLE = "default-style";
    String HTTP_REFRESH = "refresh";

    Map<String, String> getHttpEquivMetaMap();

    String getHttpEquivMeta(String httpEquiv);

    void setHttpEquivMeta(String httpEquiv, String content);

}
