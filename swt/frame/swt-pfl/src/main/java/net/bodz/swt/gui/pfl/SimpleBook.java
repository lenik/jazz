package net.bodz.swt.gui.pfl;

import static net.bodz.swt.reflect.nls.GUINLS.GUINLS;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.collection.tree.TreePath;
import net.bodz.bas.i18n.nls.NLS;
import net.bodz.bas.sio.BCharOut;

public class SimpleBook
        extends AbstractBook {

    private Map<TreePath, IPage> map;
    private TreePath first;
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
        map = new HashMap<TreePath, IPage>();
        methods = new ArrayList<PageMethod>(4);
        dict = GUINLS;
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
    protected IPage _getPage(TreePath path) {
        return map.get(path);
    }

    public Set<TreePath> getPaths() {
        return map.keySet();
    }

    public void add(String path, IPage page) {
        add(new TreePath(path), page);
    }

    public void add(Class<?> clazz, IPage page) {
        add(new TreePath(clazz.getName()), page);
    }

    public void add(TreePath path, IPage page) {
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
    protected NLS _getDict() {
        return dict;
    }

    public void setDict(NLS dict) {
        this.dict = dict;
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(map.size() * 30);
        out.println(getTitle() + ": ");
        out.println("    Pages: ");
        for (Entry<TreePath, IPage> e : map.entrySet()) {
            TreePath path = e.getKey();
            out.println("        " + path + " -> " + e.getValue());
        }
        if (methods != null && !methods.isEmpty())
            out.println("    Methods: ");
        for (PageMethod method : methods)
            out.println("        " + method);
        if (dict != null)
            out.println("    Dict: " + dict.getName());
        if (next != null) {
            out.println();
            out.println(next);
        }
        return out.toString();
    }

}
