package net.bodz.sms.ucpaas;

import java.io.IOException;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.sms.AbstractSmsImpl;
import net.bodz.sms.SmsRecord;
import net.bodz.sms.SmsTemplate;

public class UcpaasSmsImpl
        extends AbstractSmsImpl {

    IUcpaasClient client;
    UcpaasId ucid;

    boolean checkTemplate;

    public UcpaasSmsImpl(IUcpaasClient client, UcpaasId ucid) {
        if (client == null)
            throw new NullPointerException("client");
        if (ucid == null)
            throw new NullPointerException("ucid");
        this.client = client;
        this.ucid = ucid;
        if (checkTemplate)
            loadTemplates();
    }

    void loadTemplates() {
        // client.getTemplate(ucid, templateId, pageNum, pageSize);
    }

    @Override
    protected boolean canSend(SmsRecord record) {
        if (record.templateName == null)
            return false;

        if (checkTemplate) {
            SmsTemplate template = getTemplate(record.templateName);
            if (template == null)
                return false;
        }

        return true;
    }

    @Override
    protected void send(LinkedList<SmsRecord> records)
            throws IOException, ParseException {
        String uid = "callback-id";
        for (SmsRecord record : records) {
            if (record.templateName == null) // plain text isn't supported.
                continue;
            SmsTemplate template = getTemplate(record.templateName);
            if (template == null) // template is undefined.
                continue;
            String templateId = template.id.toString();
            String[] params;
            if (record.parameters == null)
                params = new String[0];
            else
                params = record.parameters.toArray(new String[0]);

            client.sendSms(ucid, uid, templateId, record.recipient, params);
        }
    }

}
