package net.bodz.sms.ucpaas;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.err.ParseException;

public interface IUcpaasClient {

    int TYPE_NOTIFICATION = 0;
    int TYPE_VERIFICATION = 4;
    int TYPE_VIP_SERVICE = 5;

    SendSmsResult sendSms(UcpaasId ucid, String uid, String templateId, String mobile, String... params)
            throws IOException, ParseException;

    SendSmsBatchResult sendSmsBatch(UcpaasId ucid, String uid, String templateId, Collection<String> mobiles,
            String... params)
            throws IOException, ParseException;

    AddTemplateResult addTemplate(UcpaasId ucid, int type, String templateName, String autograph, String content)
            throws IOException, ParseException;

    GetTemplateResult getTemplate(UcpaasId ucid, String templateId, String pageNum, String pageSize)
            throws IOException, ParseException;

    EditTemplateResult editTemplate(UcpaasId ucid, String templateId, int type, String templateName, String autograph,
            String content)
            throws IOException, ParseException;

    DeleteTemplateResult deleteTemplate(UcpaasId ucid, String templateId)
            throws IOException, ParseException;

}
