package net.bodz.xml.models.cfg;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bodz.xml.a.XMLDump;
import net.bodz.xml.util.InputSourceTrace;
import net.bodz.xml.util.JiBX;

import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

public class Modcfg extends Docobj implements InputSourceTrace {

    private Object               inputSource;

    @XMLDump
    protected URL                src;

    @XMLDump
    protected List<Link>         links;

    @XMLDump
    protected List<Docobj>       objs;

    protected Map<String, Param> params;

    public Modcfg() {
        links = new ArrayList<Link>();
        objs = new ArrayList<Docobj>();
        params = new HashMap<String, Param>();
        // settings = new HashMap<String, Setting>();
    }

    protected void pre_set(IUnmarshallingContext ictx) {
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        Object uc = ctx.getUserContext();
        System.out.println(uc);
    }

    public void setInputSource(Object inputSource) {
        this.inputSource = inputSource;
    }

    public void add(Object o) {
        if (o instanceof Docobj) {
            Docobj dobj = (Docobj) o;
            objs.add(dobj);
            if (dobj instanceof Param)
                params.put(dobj.getName(), (Param) dobj);
            // else if (dobj instanceof Setting)
            // settings.put(dobj.getName(), (Setting) dobj);
        } else
            throw new IllegalArgumentException("Unknown type: " + o.getClass());
    }

    public int size() {
        return objs.size();
    }

    public Docobj get(int index) {
        if (index < 0 || index >= objs.size())
            throw new IndexOutOfBoundsException("" + index);
        return objs.get(index);
    }

    public Iterator<Docobj> iterator() {
        return objs.iterator();
    }

    public Param getParam(String name) {
        return params.get(name);
    }

    public Map<String, Object> eval() throws FileNotFoundException,
            JiBXException {
        Map<String, Object> map = new HashMap<String, Object>();
        String dirname = JiBX.dirname(inputSource);
        for (Link link : links) {
            if (!"base".equals(link.rel))
                throw new UnsupportedOperationException(
                        "Don't support links with rel other than 'base'");
            String href = dirname + '/' + link.href;
            Object sub = JiBX.parse(Modcfg.class, href);
            Modcfg subcfg = (Modcfg) sub;
            
            // XXX - only add new ones
            params.putAll(subcfg.params);
            
            Map<String, Object> submap = subcfg.eval();
            map.putAll(submap);
        }

        obj: for (Docobj obj : objs) {
            if (obj instanceof Setting) {
                Setting set = (Setting) obj;
                for (Condition cond : set.conditions) {
                    if (!cond.test())
                        continue obj;
                }
                map.put(set.getName(), set.getValue());
            }
        }
        return map;
    }
}
