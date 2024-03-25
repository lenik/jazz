package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;

public class JsonOutFn {

    IJsonOut out;
//    int depth;

    public JsonOutFn(IJsonOut out) {
        this.out = out;
    }

//    public JsonOutFn(IJsonOut out, int depth) {
//        this.out = out;
//        this.depth = depth;
//    }

    public boolean jsonForm(IJsonForm jsonForm, JsonFormOptions opts, boolean spread)
            throws IOException, FormatException {
        if (spread) {
            if (! jsonForm.wantObjectContext())
                throw new IllegalUsageException("can't do spread");
            jsonForm.jsonOut(out, opts);
        } else {
            jsonForm.jsonOutValue(out, opts);
        }
        return true;
    }

    public void throwable(Throwable e) {
        out.object();
        try {
            out.key("type");
            out.value(e.getClass().getName());
            out.key("message");
            out.value(e.getMessage());

            Throwable cause = e.getCause();
            if (cause != null) {
                out.key("cause");
                throwable(cause);
            }
        } finally {
            out.endObject();
        }
    }

}
