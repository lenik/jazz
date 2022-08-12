package net.bodz.bas.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonStruct;
import net.bodz.bas.json.JsonObject;

public class SmsCommitLog
        extends JsonStruct
        implements ISmsListener {

    List<SmsRecord> records = new ArrayList<SmsRecord>();

    @Override
    public void sent(SmsRecord record) {
    }

    @Override
    public void failed(SmsRecord record) {
    }

    @Override
    public void ignored(SmsRecord record) {
    }

    @Override
    public void completed(SmsRecord record) {
        records.add(record);
    }

    public List<SmsRecord> getRecords() {
        return records;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.key("records");
        out.array();
        for (SmsRecord record : records)
            out.object(record);
        out.endArray();
    }

}
