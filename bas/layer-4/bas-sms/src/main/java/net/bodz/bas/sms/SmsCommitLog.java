package net.bodz.bas.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.fmt.json.JsonStruct;

public class SmsCommitLog
        extends JsonStruct
        implements ISmsListener {

    private static final long serialVersionUID = 1L;

    List<SmsRecord> records = new ArrayList<>();

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
    public void readObject(JsonObject o)
            throws ParseException {
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.key("records");
        out.array();
        for (SmsRecord record : records)
            out.object(record);
        out.endArray();
    }

}
