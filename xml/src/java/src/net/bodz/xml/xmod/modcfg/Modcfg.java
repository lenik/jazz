package net.bodz.xml.xmod.modcfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.bodz.xml.xmod.util.Docobj;
import net.sf.freejava.fp.dump.XMLDump;
import net.sf.freejava.util.ConcatIterator;

public class Modcfg extends Docobj {

    @XMLDump
    protected List<Link>           links;
    
    @XMLDump
    protected List<Docobj>         objs;
    
    protected Map<String, Param>   params;
    protected Map<String, Setting> settings;

    public Modcfg() {
        links = new ArrayList<Link>();
        objs = new ArrayList<Docobj>();
        params = new HashMap<String, Param>();
        settings = new HashMap<String, Setting>();
    }

    public void add(Object o) {
        if (o instanceof Docobj) {
            Docobj dobj = (Docobj) o;
            objs.add(dobj);
            if (dobj instanceof Param)
                params.put(dobj.getName(), (Param) dobj);
            else if (dobj instanceof Setting)
                settings.put(dobj.getName(), (Setting) dobj);
        } else
            throw new IllegalArgumentException("Unknown type: " + o.getClass());
    }

    public int size() {
        return objs.size();
    }

    public Object get(int index) {
        if (index < params.size())
            return params.get(index);
        else
            return settings.get(index);
    }

    public Iterator<Docobj> iterator() {
        return new ConcatIterator<Docobj>(params, settings);
    }

}
