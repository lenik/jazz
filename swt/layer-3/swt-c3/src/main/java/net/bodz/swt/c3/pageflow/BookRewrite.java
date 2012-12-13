package net.bodz.swt.c3.pageflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.t.pojo.PathEntries;

public class BookRewrite
        implements IBook {

    private final String title;
    private final List<RewriteRule> rules;
    private final IBook next;

    public BookRewrite(IBook next) {
        this(null, next);
    }

    public BookRewrite(String title, IBook next) {
        if (title == null)
            title = getClass().getName();
        this.title = title;
        if (next == null)
            throw new NullPointerException("next");
        this.rules = new ArrayList<RewriteRule>();
        this.next = next;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public PathEntries getFirst() {
        return next.getFirst();
    }

    @Override
    public boolean contains(PathEntries path) {
        path = resolve(path);
        return next.contains(path);
    }

    @Override
    public IPage getPage(PathEntries path) {
        path = resolve(path);
        return next.getPage(path);
    }

    @Override
    public Collection<PageMethod> getMethods() {
        return next.getMethods();
    }

    public int getRuleCount() {
        return rules.size();
    }

    public RewriteRule getRule(int index) {
        return rules.get(index);
    }

    public void addRule(RewriteRule rule) {
        rules.add(rule);
    }

    public void addRule(int index, RewriteRule rule) {
        rules.add(index, rule);
    }

    public void removeRule(RewriteRule rule) {
        rules.remove(rule);
    }

    public void removeRule(int index) {
        rules.remove(index);
    }

    public PathEntries resolve(PathEntries treePath) {
        if (treePath == null)
            return null;
        String path = treePath.getPath();
        R: do {
            for (RewriteRule rule : rules) {
                String dst = rule.rewrite(path);
                if (path.equals(dst))
                    continue;
                path = dst;
                int flags = rule.getFlags();
                if ((flags & RewriteRule.LAST) != 0)
                    break;
                if ((flags & RewriteRule.RESTART) != 0)
                    continue R;
            }
        } while (false);
        return new PathEntries(path);
    }

    @Override
    public NLS getDict() {
        return next.getDict();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(rules.size() * 30);
        buf.append(getTitle() + ": \n");
        buf.append("    Rewrite Rules: \n");
        for (RewriteRule rule : rules) {
            buf.append("        " + rule + "\n");
        }
        buf.append("\n");
        buf.append(next);
        return buf.toString();
    }

}
