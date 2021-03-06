package net.bodz.bas.i18n.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoZone {

    private final String localCode;
    private final String localeName;

    private String enName;
    private String zhName;

    private final GeoZone parent;
    private final List<GeoZone> children = new ArrayList<GeoZone>();;

    public GeoZone(String localCode, String localeName, GeoZone parent) {
        this.localCode = localCode;
        this.localeName = localeName;
        this.parent = parent;
        if (parent != null)
            parent.children.add(this);
    }

    public List<GeoZone> bottomUp() {
        List<GeoZone> list = new ArrayList<GeoZone>();
        GeoZone node = this;
        while (node != null) {
            list.add(node);
            node = node.getParent();
        }
        return list;
    }

    public List<GeoZone> topDown() {
        List<GeoZone> list = bottomUp();
        Collections.reverse(list);
        return list;
    }

    public String getLocalCode() {
        return localCode;
    }

    public String getFullCode() {
        return buildCode();
    }

    public String buildCode() {
        StringBuilder sb = new StringBuilder(16);
        for (GeoZone r : topDown())
            sb.append(r.localCode);
        return sb.toString();
    }

    public String getLocaleName() {
        return localeName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public GeoZone getParent() {
        return parent;
    }

    public List<GeoZone> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString(", ");
    }

    public String toString(String delimit) {
        StringBuilder sb = new StringBuilder();
        GeoZone r = this;
        while (true) {
            sb.append(r.enName);
            r = r.parent;
            if (r != null)
                sb.append(delimit);
            else
                break;
        }
        return sb.toString();
    }

    public String toZhString() {
        return toZhString(" ");
    }

    public String toZhString(String delimit) {
        StringBuilder sb = new StringBuilder();
        buildZhString(sb, delimit);
        return sb.toString();
    }

    private void buildZhString(StringBuilder sb, String delimit) {
        if (parent != null) {
            parent.buildZhString(sb, delimit);
            sb.append(delimit);
        }
        sb.append(zhName);
    }

}
