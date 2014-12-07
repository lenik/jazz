package net.bodz.bas.i18n.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Region {

    private final String code;
    private final String localeName;

    private String enName;
    private String zhName;

    private final Region parent;
    private final List<Region> children = new ArrayList<Region>();;

    public Region(String code, String localeName, Region parent) {
        this.code = code;
        this.localeName = localeName;
        this.parent = parent;
        if (parent != null)
            parent.children.add(this);
    }

    public List<Region> bottomUp() {
        List<Region> list = new ArrayList<>();
        Region node = this;
        while (node != null) {
            list.add(node);
            node = node.getParent();
        }
        return list;
    }

    public List<Region> topDown() {
        List<Region> list = bottomUp();
        Collections.reverse(list);
        return list;
    }

    public String getCode() {
        return code;
    }

    public String joinId() {
        StringBuilder sb = new StringBuilder(16);
        for (Region r : topDown())
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

    public Region getParent() {
        return parent;
    }

    public List<Region> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return toString(", ");
    }

    public String toString(String delimit) {
        StringBuilder sb = new StringBuilder();
        Region r = this;
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
