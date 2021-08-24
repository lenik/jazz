package net.bodz.bas.site.json;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.log.ILogger;
import net.bodz.bas.repr.form.SortOrder;

public interface IMutableJsonResponse
        extends
            IJsonResponse {

    IJsonResponse succeed();

    IJsonResponse fail();

    IJsonResponse fail(int status);

    IJsonResponse fail(String messageFormat, Object... args);

    IJsonResponse fail(String message);

    IJsonResponse fail(int status, String messageFormat, Object... args);

    IJsonResponse fail(int status, String message);

    IJsonResponse fail(Throwable e);

    IJsonResponse fail(Throwable e, int status);

    IJsonResponse fail(Throwable e, String messageFormat, Object... args);

    IJsonResponse fail(Throwable e, String message);

    IJsonResponse fail(Throwable e, int status, String messageFormat, Object... args);

    IJsonResponse fail(Throwable e, int status, String message);

    void setStatus(int status);

    void setMessage(String message);

    void setException(Throwable exception);

    void setData(Object js_val);

    void setHeaderOrder(SortOrder headerOrder);

    IJsonResponse setHeader(String header, Object content);

    /**
     * Begins a new section.
     */
    JsonWriter begin(String key);

    ILogger getLogger();

}
