package net.bodz.bas.site.json;

import java.util.Map;

import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.repr.form.SortOrder;

public interface IJsonResponse
        extends
            IJsonForm {

    int OK = 0;
    int WARN = 300;
    int ERROR = 400;
    int FATAL_ERROR = 500;

    boolean isSuccess();

    boolean isError();

    int getStatus();

    String getMessage();

    Throwable getException();

    Object getData();

    SortOrder getHeaderOrder();

    Map<String, Object> getHeaders();

    Object getHeader(String header);

    JsonSection getRoot();

}
