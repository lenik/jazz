package net.bodz.bas.repr.html;

import net.bodz.bas.meta.build.VersionRange;
import net.bodz.bas.t.order.IMutablePriority;

public class MutableRequirement
        implements IRequirement, IMutablePriority {

    private int priority;
    private String id;
    private String type;
    private VersionRange versionRange;
    private boolean optional;
    private String url;
    private String data;

    public MutableRequirement(String id, String type, VersionRange versionRange) {
        if (id == null)
            throw new NullPointerException("id");
        if (type == null)
            throw new NullPointerException("type");
        if (versionRange == null)
            throw new NullPointerException("versionRange");
        this.id = id;
        this.type = type;
        this.versionRange = versionRange;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null)
            throw new NullPointerException("id");
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    @Override
    public VersionRange getVersionRange() {
        return versionRange;
    }

    public void setVersionRange(VersionRange versionRange) {
        if (versionRange == null)
            throw new NullPointerException("versionRange");
        this.versionRange = versionRange;
    }

    @Override
    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    @Override
    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int compareTo(IRequirement o) {
        if (o == null)
            return 1;
        VersionRange range1 = getVersionRange();
        VersionRange range2 = o.getVersionRange();
        return range1.compareTo(range2);
    }

}
