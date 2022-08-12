package net.bodz.bas.site.file;

import java.io.IOException;
import java.util.ArrayList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class UploadResult
        extends ArrayList<UploadedFileInfo>
        implements
            IJsonForm {

    private static final long serialVersionUID = 1L;

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        JsonArray files = o.getJsonArray("files");
        int nfile = files.length();
        for (int i = 0; i < nfile; i++) {
            UploadedFileInfo file = files.readInto(i, new UploadedFileInfo(), opts);
            add(file);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.key("files");
        {
            out.array();
            for (UploadedFileInfo item : this) {
                out.object();
                item.jsonOut(out, opts);
                out.endObject();
            }
            out.endArray();
        }
    }

}
