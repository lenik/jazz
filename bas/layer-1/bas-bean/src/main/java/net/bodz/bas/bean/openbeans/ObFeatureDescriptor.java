package net.bodz.bas.bean.openbeans;

import java.util.Enumeration;

import net.bodz.bas.bean.api.IFeatureDescriptor;

import com.googlecode.openbeans.FeatureDescriptor;

public class ObFeatureDescriptor
        implements
            IFeatureDescriptor {

    FeatureDescriptor fd;

    public ObFeatureDescriptor(FeatureDescriptor fd) {
        if (fd == null)
            throw new NullPointerException("fd");
        this.fd = fd;
    }

    @Override
    public String getName() {
        return fd.getName();
    }

    @Override
    public void setName(String name) {
        fd.setName(name);
    }

    @Override
    public String getDisplayName() {
        return fd.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName) {
        fd.setDisplayName(displayName);
    }

    @Override
    public boolean isExpert() {
        return fd.isExpert();
    }

    @Override
    public void setExpert(boolean expert) {
        fd.setExpert(expert);
    }

    @Override
    public boolean isHidden() {
        return fd.isHidden();
    }

    @Override
    public void setHidden(boolean hidden) {
        fd.setHidden(hidden);
    }

    @Override
    public boolean isPreferred() {
        return fd.isPreferred();
    }

    @Override
    public void setPreferred(boolean preferred) {
        fd.setPreferred(preferred);
    }

    @Override
    public String getShortDescription() {
        return fd.getShortDescription();
    }

    @Override
    public void setShortDescription(String text) {
        fd.setShortDescription(text);
    }

    @Override
    public void setValue(String attributeName, Object value) {
        fd.setValue(attributeName, value);
    }

    @Override
    public Object getValue(String attributeName) {
        return fd.getValue(attributeName);
    }

    @Override
    public Enumeration<String> attributeNames() {
        return fd.attributeNames();
    }

    @Override
    public boolean equals(Object obj) {
        return fd.equals(obj);
    }

    @Override
    public int hashCode() {
        return fd.hashCode();
    }

    @Override
    public String toString() {
        return fd.toString();
    }

    public static ObFeatureDescriptor convert(FeatureDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObFeatureDescriptor(o);
    }

    public static ObFeatureDescriptor[] convert(FeatureDescriptor[] src) {
        if (src == null)
            return null;
        ObFeatureDescriptor[] dst = new ObFeatureDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
