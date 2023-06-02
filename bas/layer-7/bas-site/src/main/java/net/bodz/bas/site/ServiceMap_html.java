package net.bodz.bas.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.io.tag.HtmlLi;
import net.bodz.bas.html.io.tag.HtmlUl;
import net.bodz.bas.html.viz.AbstractHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.bas.ui.dom1.IUiRef;

public class ServiceMap_html
        extends AbstractHtmlViewBuilder<ServiceMap> {

    @Override
    public IHtmlOut buildHtmlViewStart(IHtmlViewContext ctx, IHtmlOut out, IUiRef<ServiceMap> ref)
            throws ViewBuilderException, IOException {
        ServiceMap map = ref.get();

        List<Entry> list = new ArrayList<>(map.size());
        for (String key : map.keySet()) {
            Object service = map.get(key);
            Entry ent = new Entry(key, service);
            list.add(ent);
        }
        Collections.sort(list, byClass);

        HtmlUl ul = out.ul();
        {
            for (Entry entry : list) {
                HtmlLi li = ul.li();
                li.a().href(entry.key + "/").text(entry.key);
                li.div().text(entry.serviceClass.getCanonicalName());
                li.end();
            }
            ul.end();
        }
        return null;
    }

    static class Entry {

        String key;
        Object service;
        Class<?> serviceClass;

        public Entry(String key, Object service) {
            this.key = key;
            this.service = service;
            this.serviceClass = service.getClass();
        }

    }

    static Comparator<Entry> byClass = new AbstractNonNullComparator<Entry>() {

        @Override
        public int compareNonNull(Entry o1, Entry o2) {
            Class<?> c1 = o1.serviceClass;
            Class<?> c2 = o2.serviceClass;
            int cmp = c1.getName().compareTo(c2.getName());
            if (cmp != 0)
                return cmp;
            cmp = o1.key.compareTo(o2.key);
            return cmp;
        }

    };

}
