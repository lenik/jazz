package net.bodz.swt.c.pageflow;

import static net.bodz.swt.nls.GUINLS.GUINLS;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.pojo.PathEntries;

public class SimpleBook
        extends AbstractBook {

    private Map<PathEntries, IPage> map;
    private PathEntries first;
    private List<PageMethod> methods;
    private NLS dict;

    public SimpleBook() {
        this(null, null);
    }

    public SimpleBook(String title) {
        this(title, null);
    }

    public SimpleBook(IBook next) {
        this(null, next);
    }

    public SimpleBook(String title, IBook next) {
        super(title, next);
        map = new HashMap<PathEntries, IPage>();
        methods = new ArrayList<PageMethod>(4);
        dict = GUINLS;
    }

    @Override
    protected PathEntries _getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = new PathEntries(first);
    }

    public void setFirst(Class<?> first) {
        this.first = new PathEntries(first.getName());
    }

    public void setFirst(PathEntries first) {
        this.first = first;
    }

    @Override
    protected boolean _contains(PathEntries path) {
        return map.containsKey(path);
    }

    @Override
    protected IPage _getPage(PathEntries path) {
        return map.get(path);
    }

    public Set<PathEntries> getPaths() {
        return map.keySet();
    }

    public void add(String path, IPage page) {
        add(new PathEntries(path), page);
    }

    public void add(Class<?> clazz, IPage page) {
        add(new PathEntries(clazz.getName()), page);
    }

    public void add(PathEntries path, IPage page) {
        map.put(path, page);
    }

    public boolean remove(String path) {
        return remove(new PathEntries(path));
    }

    public boolean remove(PathEntries path) {
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
    protected NLS _getDict() {
        return dict;
    }

    public void setDict(NLS dict) {
        this.dict = dict;
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut(map.size() * 30);
        buf.println(getTitle() + ": ");
        buf.println("    Pages: ");
        for (Entry<PathEntries, IPage> e : map.entrySet()) {
            PathEntries path = e.getKey();
            buf.println("        " + path + " -> " + e.getValue());
        }
        if (methods != null && !methods.isEmpty())
            buf.println("    Methods: ");
        for (PageMethod method : methods)
            buf.println("        " + method);
        if (dict != null)
            buf.println("    Dict: " + dict.getName());
        if (next != null) {
            buf.println();
            buf.println(next);
        }
        return buf.toString();
    }

}
