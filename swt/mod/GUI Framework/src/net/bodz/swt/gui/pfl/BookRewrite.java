package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.text.locale.NLSDict;
import net.bodz.bas.types.TreePath;

public class BookRewrite implements Book {

    private final String            title;
    private final List<RewriteRule> rules;
    private final Book              next;

    public BookRewrite(Book next) {
        this(null, next);
    }

    public BookRewrite(String title, Book next) {
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
    public TreePath getFirst() {
        return next.getFirst();
    }

    @Override
    public boolean contains(TreePath path) {
        path = resolve(path);
        return next.contains(path);
    }

    @Override
    public Page getPage(TreePath path) {
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

    public TreePath resolve(TreePath treePath) {
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
        return new TreePath(path);
    }

    @Override
    public NLSDict getDict() {
        return next.getDict();
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(rules.size() * 30);
        out.println(getTitle() + ": ");
        out.println("    Rewrite Rules:");
        for (RewriteRule rule : rules) {
            out.println("        " + rule);
        }
        out.println();
        out.print(next);
        return out.toString();
    }

}
