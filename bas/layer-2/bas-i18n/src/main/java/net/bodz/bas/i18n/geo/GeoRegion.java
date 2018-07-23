package net.bodz.bas.i18n.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoRegion {

    private final String code;
    private final String localeName;

    private String enName;
    private String zhName;

    private final GeoRegion parent;
    private final List<GeoRegion> children = new ArrayList<GeoRegion>();;

    public GeoRegion(String code, String localeName, GeoRegion parent) {
        this.code = code;
        this.localeName = localeName;
        this.parent = parent;
        if (parent != null)
            parent.children.add(this);
    }

    public List<GeoRegion> bottomUp() {
        List<GeoRegion> list = new ArrayList<>();
        GeoRegion node = this;
        while (node != null) {
            list.add(node);
            node = node.getParent();
        }
        return list;
    }

    public List<GeoRegion> topDown() {
        List<GeoRegion> list = bottomUp();
        Collections.reverse(list);
        return list;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return buildId();
    }

    public String buildId() {
        StringBuilder sb = new StringBuilder(16);
        for (GeoRegion r : topDown())
            sb.append(r.code);
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

    public GeoRegion getParent() {
        return parent;
    }

    public List<GeoRegion> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString(", ");
    }

    public String toString(String delimit) {
        StringBuilder sb = new StringBuilder();
        GeoRegion r = this;
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
