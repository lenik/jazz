package net.bodz.bas.sms.ucpaas;

import java.io.IOException;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.sms.ucpaas.*;

public class DefaultUcpaasClientTest
        extends Assert {

    static DefaultUcpaasClient client = DefaultUcpaasClient.getInstance();

    static final UcpaasId ucid;
    static {
        ucid = new UcpaasId();
        ucid.appid = "2cfbd61bf2b2460ab0087304fb2b0b64";
        ucid.sid = "890c213aeb898a7490b3b14db98b63b5";
        ucid.token = "aa3fd35c9ff72bec5d457ea4ae12adb4";
    }

    static AddTemplateResult ats;

    @BeforeClass
    public static void setup()
            throws IOException, ParseException {
        ats = client.addTemplate(ucid, IUcpaasClient.TYPE_VERIFICATION, "name", "auto", "content{1}");
        System.out.println("Add template: " + ats);
        ats.ensureSucceded();
    }

    @AfterClass
    public static void cleanup()
            throws IOException, ParseException {
        DeleteTemplateResult result = client.deleteTemplate(ucid, ats.templateId);
        System.out.println("Delete template: " + result);
        result.ensureSucceded();
    }

    @Test
    public void testSendSms()
            throws IOException, ParseException {
        SendSmsResult result = client.sendSms(ucid, "1234user", "544801", "00000", "KHFD", "3");
        System.out.println("Send sms: " + result);
        result.ensureSucceded();
    }

    @Test
    public void testSendSmsBatch()
            throws IOException, ParseException {
        SendSmsBatchResult result = client
                .sendSmsBatch(ucid, "1234user", "544801", Arrays.asList("00000"), "KHFD", "3");
        System.out.println("Send sms batch: " + result);
        result.ensureSucceded();
    }

    @Test
    public void testGetTemplate()
            throws IOException, ParseException {
        GetTemplateResult result = client.getTemplate(ucid, ats.templateId, null, null);
        System.out.println("Get template: " + result);
        result.ensureSucceded();
    }

    @Test
    public void testEditTemplate()
            throws IOException, ParseException {
        String templateId = ats.templateId;
        EditTemplateResult result = client.editTemplate(ucid, templateId, IUcpaasClient.TYPE_NOTIFICATION, "newname",
                "newgraph", "new content{1}");
        System.out.println("Edit template: " + result);
        result.ensureSucceded();
    }

    public static void main(String[] args)
            throws Exception {
        SendSmsResult result = client.sendSms(ucid, "1234user", "544801", "13819471680", "KHFD", "3");
        System.out.println("Send sms: " + result);
        result.ensureSucceded();
    }

}
