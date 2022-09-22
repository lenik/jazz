package net.bodz.lily.model.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPreps;

public class LocalFileDebugUtils {

    static Pattern img_findsrchref = Pattern.compile("<img src=\"(.*?)\"", Pattern.CASE_INSENSITIVE);

    Pattern pattern = img_findsrchref;

    /**
     * @param urlPrefix
     *            like <code>http://example.com</code>. Without the trailing slash.
     */
    public String convertAbsolutePathToAbsoluteUrl(String html, String urlPrefix) {
        String modified = TextPreps.match(img_findsrchref, new IPartProcessor() {
            @Override
            public void process(CharSequence in, int start, int end, Appendable out, Matcher matcher)
                    throws IOException {
                String href = matcher.group(1);
                String newHref = null;
                if (href.startsWith("/")) {
                    newHref = urlPrefix + href;
                }
                if (newHref != null)
                    out.append("<img src=\"" + newHref + "\"");
                else
                    out.append(in, start, end);
            }
        }).process(html);
        return modified;
    }

    public String convertAbsoluteUrlToAbsolutePath(String html) {
        String modified = TextPreps.match(img_findsrchref, new IPartProcessor() {
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
        return modified;
    }

}
