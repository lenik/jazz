package net.bodz.mda.xmm.initial;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.lang.Filt1;
import net.bodz.bas.text.util.PatternProcessor;

public class EnclosedTags extends PatternProcessor {

    static Pattern         region;
    static Pattern         enclosingPrefix;
    static Pattern         enclosingSuffix;
    static {
        region = Pattern.compile("<(\\w+)>(.*?)</\\1>", Pattern.DOTALL);
        enclosingPrefix = Pattern.compile("(?://|/\\*)\\s*$");
        enclosingSuffix = Pattern.compile("^\\s*?(?:\\*/|\n)");
    }

    private Map<String, ?> tagMap;
    private boolean        first;
    private final boolean  last = false;   // always unknown.

    public EnclosedTags(Map<String, ?> tagMap) {
        super(region);
        this.tagMap = tagMap;
    }

    public EnclosedTags() {
        this(null);
    }

    static String trim(String s, Pattern p) {
        Matcher m = p.matcher(s);
        if (!m.find())
            return s;
        // the body is killed
        String head = s.substring(0, m.start());
        String foot = s.substring(m.end());
        return head + foot;
    }

    @Override
    protected void matched(int start, int end) {
        String tag = matcher.group(1);
        String enclosed = matcher.group(2);
        // enclosed.trimLeft(enclosingSuffix)
        enclosed = trim(enclosed, enclosingSuffix);
        // enclosed.trimRight(enclosingPrefix)
        enclosed = trim(enclosed, enclosingPrefix);
        processTag(tag, enclosed);
        first = false;
    }

    protected void processTag(String tag, String text) {
        if (tagMap != null) {
            Object obj = tagMap.get(tag);
            while (obj != null) {
                String repl;
                if (obj instanceof Filt1) {
                    @SuppressWarnings("unchecked")
                    Filt1<?, String> filter = (Filt1<?, String>) obj;
                    obj = filter.filter(text);
                    continue;
                } else
                    repl = String.valueOf(obj);
                print(repl);
                return;
            }
        }
        print(text);
    }

    @Override
    protected void unmatched(String part) {
        if (!first) {
            // part.trimLeft(enclosingSuffix)
            part = trim(part, enclosingSuffix);
        }
        if (!last) {
            // part.trimRight(enclosingPrefix)
            part = trim(part, enclosingPrefix);
        }
        super.unmatched(part);
    }

    @Override
    public synchronized String process(String source) {
        first = true;
        return super.process(source);
    }

}
