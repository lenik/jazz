package net.bodz.bas.site.org;

import net.bodz.bas.i18n.dom1.AbstractElement;

public class SiteGraphRelation
        extends AbstractElement {

    private String name;
    private SiteGraphNode src;
    private SiteGraphNode dst;

    public SiteGraphRelation(String name, SiteGraphNode src, SiteGraphNode dst) {
        this.name = name;
        this.src = src;
        this.dst = dst;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SiteGraphNode getSrc() {
        return src;
    }

    public void setSrc(SiteGraphNode src) {
        this.src = src;
    }

    public SiteGraphNode getDst() {
        return dst;
    }

    public void setDst(SiteGraphNode dst) {
        this.dst = dst;
    }

}
