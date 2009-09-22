package net.bodz.swt.gui.pfl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.text.locale.NLSDict;
import net.bodz.bas.text.locale.ResourceBundleDict;
import net.bodz.bas.types.TreePath;
import net.bodz.swt.nls.GUINLS;

public class SimpleBook extends _Book {

    private Map<TreePath, Page> map;
    private TreePath            first;
    private List<PageMethod>    methods;
    private NLSDict             dict;

    public SimpleBook() {
        this(null, null);
    }

    public SimpleBook(String title) {
        this(title, null);
    }

    public SimpleBook(Book next) {
        this(null, next);
    }

    public SimpleBook(String title, Book next) {
        super(title, next);
        map = new HashMap<TreePath, Page>();
        methods = new ArrayList<PageMethod>(4);
        dict = new ResourceBundleDict("GUI Dict", GUINLS.bundle);
    }

    @Override
    protected TreePath _getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = new TreePath(first);
    }

    public void setFirst(Class<?> first) {
        this.first = new TreePath(first.getName());
    }

    public void setFirst(TreePath first) {
        this.first = first;
    }

    @Override
    protected boolean _contains(TreePath path) {
        return map.containsKey(path);
    }

    @Override
    protected Page _getPage(TreePath path) {
        return map.get(path);
    }

    public Set<TreePath> getPaths() {
        return map.keySet();
    }

    public void add(String path, Page page) {
        add(new TreePath(path), page);
    }

    public void add(Class<?> clazz, Page page) {
        add(new TreePath(clazz.getName()), page);
    }

    public void add(TreePath path, Page page) {
        map.put(path, page);
    }

    public boolean remove(String path) {
        return remove(new TreePath(path));
    }

    public boolean remove(TreePath path) {
        return map.remove(path) != null;
    }

    @Override
    protected List<PageMethod> _getMethods() {
        return Collections.unmodifiableList(methods);
    }

    public void addMethod(PageMethod method) {
        if (method == null)
            throw new NullPointerException("method");
        methods.add(method);
    }

    public boolean removeMethod(PageMethod method) {
        if (methods == null)
            return false;
        return methods.remove(method);
    }

    @Override
    protected NLSDict _getDict() {
        return dict;
    }

    public void setDict(NLSDict dict) {
        this.dict = dict;
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(map.size() * 30);
        out.println(getTitle() + ": ");
        out.println("    Pages: ");
        for (Entry<TreePath, Page> e : map.entrySet()) {
            TreePath path = e.getKey();
            out.println("        " + path + " -> " + e.getValue());
        }
        if (methods != null && !methods.isEmpty())
            out.println("    Methods: ");
        for (PageMethod method : methods)
            out.println("        " + method);
        if (dict != null)
            out.println("    Dict: " + dict.getTitle());
        if (next != null) {
            out.println();
            out.println(next);
        }
        return out.toString();
    }

}
