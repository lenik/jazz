package net.bodz.bas.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.fork.org.json.JSONException;

public class HttpClients {

    public static JsonVariant postJson(String url, String body)
            throws IOException, ParseException {
        return postJson(url, body, "utf-8", "utf-8");
    }

    public static JsonVariant postJson(String url, String body, String requestCharset, String fallbackCharset)
            throws ClientProtocolException, IOException, ParseException {
        HttpClient client = HttpClientConfig.DEFAULT.newClient();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(HttpRequestConfig.DEFAULT.compile());

        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setEntity(new StringEntity(body, requestCharset));

        HttpResponse response;
        // try {
        response = client.execute(httpPost);
        // } catch (ClientProtocolException e) {
        // throw new ParseException(e);
        // }

        HttpEntity responseEntity = response.getEntity();
        if (responseEntity == null)
            return null;

        String charset = fallbackCharset;
        if (response.containsHeader("content-type")) {
            // content-type: text/html; charset=...
            String s = response.getLastHeader("content-type").getValue();
            int pos = s.indexOf(';');
            L: while (pos != -1) {
                s = s.substring(pos + 1);
                pos = s.indexOf("=");
                if (pos == -1)
                    break;
                String k = s.substring(0, pos).trim();
                if ("charset".equals(k)) {
                    charset = s.substring(pos + 1).trim();
                    break L;
                }
            }
        }

        String result = EntityUtils.toString(responseEntity, charset);
        try {
            JsonVariant jsonVar = JsonFn.parseAny(result);
            return jsonVar;
        } catch (JSONException e) {
            throw new ParseException(e);
        }
    }

}
