package net.bodz.bas.sms.ucpaas;

import java.io.IOException;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.sms.AbstractSmsImpl;
import net.bodz.bas.sms.SmsRecord;
import net.bodz.bas.sms.SmsTemplate;

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

        SmsTemplate template = getTemplate(record.templateName);
        if (template == null)
            return false;

        return true;
    }

    @Override
    protected void send(LinkedList<SmsRecord> records)
            throws IOException, ParseException {
        for (SmsRecord record : records) {
            if (record.templateName == null) // plain text isn't supported.
                continue;
            SmsTemplate template = getTemplate(record.templateName);
            if (template == null) // template is undefined.
                continue;
            String templateId = template.id.toString();
            String[] params = record.getParametersAsStringArray();
            SendSmsResult result = client.sendSms(//
                    ucid, record.uid, templateId, record.recipient, params);
            record.response = result;
        }
    }

}
