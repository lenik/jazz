package net.bodz.bas.sms.ucpaas;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.c.org.json.JsonBuffer;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.http.HttpClients;
import net.bodz.bas.json.JsonObject;

public class DefaultUcpaasClient
        implements
            IUcpaasClient {

    JsonFormOptions jsonOpts = JsonFormOptions.DEFAULT;

    /**
     * @return Result with no row means something wrong.
     */
    @Override
    public SendSmsResult sendSms(UcpaasId ucid, String uid, String templateId, String mobile, String... params)
            throws IOException, ParseException {
        String url = getUrl("sendsms");
        JsonBuffer sendContent = new JsonBuffer();
        sendContent.object();
        {
            ucid.jsonOut(sendContent, JsonFormOptions.DEFAULT);
            sendContent.entry("templateid", templateId);
            sendContent.entry("param", StringArray.join(",", params));
            sendContent.entry("mobile", mobile);
            sendContent.entry("uid", uid);
        }
        sendContent.endObject();
        JsonObject jo = HttpClients.postJson(url, sendContent.toString());
        SendSmsResult result = new SendSmsResult();
        result.jsonIn(jo, JsonFormOptions.DEFAULT);
        return result;
    }

    @Override
    public SendSmsBatchResult sendSmsBatch(UcpaasId ucid, String uid, String templateId, Collection<String> mobiles,
            String... params)
            throws IOException, ParseException {
        String url = getUrl("sendsms_batch");
        JsonBuffer js = new JsonBuffer();
        js.object();
        {
            ucid.jsonOut(js, jsonOpts);
            js.entry("templateid", templateId);
            js.entry("param", StringArray.join(",", params));
            js.entry("mobile", StringArray.join(",", mobiles));
            js.entry("uid", uid);
        }
        js.endObject();
        JsonObject jo = HttpClients.postJson(url, js.toString());
        SendSmsBatchResult result = new SendSmsBatchResult();
        result.jsonIn(jo, jsonOpts);
        return result;
    }

    @Override
    public AddTemplateResult addTemplate(UcpaasId ucid, int type, String templateName, String autograph, String content)
            throws IOException, ParseException {
        String url = getUrl("addsmstemplate");
        JsonBuffer js = new JsonBuffer();
        js.object();
        {
            ucid.jsonOut(js, jsonOpts);
            js.entry("type", "" + type);
            js.entry("template_name", templateName);
            js.entry("autograph", autograph);
            js.entry("content", content);
        }
        js.endObject();
        JsonObject jo = HttpClients.postJson(url, js.toString());
        AddTemplateResult result = new AddTemplateResult();
        result.jsonIn(jo, jsonOpts);
        return result;
    }

    @Override
    public GetTemplateResult getTemplate(UcpaasId ucid, String templateId, String page_num, String page_size)
            throws IOException, ParseException {
        String url = getUrl("getsmstemplate");
        JsonBuffer js = new JsonBuffer();
        js.object();
        {
            ucid.jsonOut(js, jsonOpts);
            js.entry("templateid", templateId);
            js.entry("page_num", page_num);
            js.entry("page_size", page_size);
        }
        js.endObject();
        JsonObject jo = HttpClients.postJson(url, js.toString());
        GetTemplateResult result = new GetTemplateResult();
        result.jsonIn(jo, jsonOpts);
        return result;
    }

    @Override
    public EditTemplateResult editTemplate(UcpaasId ucid, String templateId, int type, String templateName,
            String autograph, String content)
            throws IOException, ParseException {
        String url = getUrl("editsmstemplate");
        JsonBuffer js = new JsonBuffer();
        js.object();
        {
            ucid.jsonOut(js, jsonOpts);
            js.entry("templateid", templateId);
            js.entry("type", type);
            js.entry("template_name", templateName);
            js.entry("autograph", autograph);
            js.entry("content", content);
        }
        js.endObject();
        JsonObject jo = HttpClients.postJson(url, js.toString());
        EditTemplateResult result = new EditTemplateResult();
        result.jsonIn(jo, jsonOpts);
        return result;
    }

    @Override
    public DeleteTemplateResult deleteTemplate(UcpaasId ucid, String templateId)
            throws IOException, ParseException {
        String url = getUrl("deletesmstemplate");
        JsonBuffer js = new JsonBuffer();
        js.object();
        {
            ucid.jsonOut(js, jsonOpts);
            js.entry("templateid", templateId);
        }
        js.endObject();
        JsonObject jo = HttpClients.postJson(url, js.toString());
        DeleteTemplateResult result = new DeleteTemplateResult();
        result.jsonIn(jo, jsonOpts);
        return result;
    }

    String getUrl(String fn) {
        StringBuffer sb = new StringBuffer("https://");
        sb.append("open.ucpaas.com");
        sb.append("/ol/sms/");
        sb.append(fn);
        return sb.toString();
    }

    static DefaultUcpaasClient instance = new DefaultUcpaasClient();

    public static DefaultUcpaasClient getInstance() {
        return instance;
    }

}
