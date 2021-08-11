package net.bodz.lily.t.base;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPrepByParts;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;

public abstract class CoMessageIndex<T extends CoMessage<?>, M extends CoMessageMask>
        extends CoIndex<T, M> {

    static Pattern imgsrc = Pattern.compile("<img src=\"(.*?)\"", Pattern.CASE_INSENSITIVE);

    @Override
    protected T postLoad(T obj) {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        // Notice: getHeader IS case-sensitive.
        if (request != null && "file://".equals(request.getHeader("Origin"))) {
            String html = obj.getText();
            final String host = request.getHeader("Host");
            String mod = TextPrepByParts.match(imgsrc, new IPartProcessor() {
                @Override
                public void process(CharSequence in, int start, int end, Appendable out, Matcher matcher)
                        throws IOException {
                    String href = matcher.group(1);
                    String newHref = null;
                    if (href.startsWith("/")) {
                        newHref = "http://" + host + href;
                    }
                    if (newHref != null)
                        out.append("<img src=\"" + newHref + "\"");
                    else
                        out.append(in, start, end);
                }
            }).process(html);
            obj.setText(mod);
        }
        return obj;
    }

    @Override
    protected void preSave(IVariantMap<String> q, T obj, JsonResponse resp)
            throws IOException {
        String html = obj.getText();
        String mod = stripAbsoluteUrls(html);
        obj.setText(mod);
        super.preSave(q, obj, resp);
    }

    String stripAbsoluteUrls(String html) {
        return TextPrepByParts.match(imgsrc, new IPartProcessor() {
            @Override
            public void process(CharSequence in, int start, int end, Appendable out, Matcher matcher)
                    throws IOException {
                String href = matcher.group(1);
                String newHref = null;
                if (href.startsWith("http://")) {
                    String s = href.substring(7);
                    int pos = s.indexOf('/');
                    if (pos != -1)
                        newHref = s.substring(pos);
                }
                if (newHref != null)
                    out.append("<img src=\"" + newHref + "\"");
                else
                    out.append(in, start, end);
            }
        }).process(html);
    }

    @Override
    protected void renameUrlAsFileChange(T obj, File oldName, File newName, String relativePath) {
        String html = obj.getText();

    }

}
