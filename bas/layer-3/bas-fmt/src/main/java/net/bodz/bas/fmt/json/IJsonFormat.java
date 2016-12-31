package net.bodz.bas.fmt.json;

import java.io.IOException;

import org.json.JSONWriter;

public interface IJsonFormat {

    boolean writeEntryOverride(JSONWriter out, String name)
            throws IOException;

}
