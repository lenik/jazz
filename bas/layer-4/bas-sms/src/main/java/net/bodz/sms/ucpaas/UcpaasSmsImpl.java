package net.bodz.sms.ucpaas;

import java.io.IOException;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.sms.AbstractSmsImpl;
import net.bodz.sms.SmsRecord;

public class UcpaasSmsImpl
        extends AbstractSmsImpl {

    IUcpaasClient client;
    UcpaasId ucid;

    public UcpaasSmsImpl(IUcpaasClient client, UcpaasId ucid) {
        if (client == null)
            throw new NullPointerException("client");
        if (ucid == null)
            throw new NullPointerException("ucid");
        this.client = client;
        this.ucid = ucid;
    }

    @Override
    protected boolean canSend(SmsRecord record) {
        return true;
    }

    @Override
    protected void send(LinkedList<SmsRecord> records)
            throws IOException, ParseException {
        String uid = "callback-id";
        for (SmsRecord record : records) {
            String templateId = record.preparedId;
            String[] params;
            if (record.parameters == null)
                params = new String[0];
            else
                params = record.parameters.toArray(new String[0]);

            client.sendSms(ucid, uid, templateId, record.recipient, params);
        }
    }

}
