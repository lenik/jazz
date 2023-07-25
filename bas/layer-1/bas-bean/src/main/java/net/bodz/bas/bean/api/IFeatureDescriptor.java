package net.bodz.bas.bean.api;

import java.util.Enumeration;

public interface IFeatureDescriptor {

    String getName();

    void setName(String name);

    String getDisplayName();

    void setDisplayName(String displayName);

    Object getValue(String attributeName);

    void setValue(String attributeName, Object value);

    String getShortDescription();

    void setShortDescription(String text);

    boolean isPreferred();

    void setPreferred(boolean preferred);

    boolean isHidden();

    void setHidden(boolean hidden);

    boolean isExpert();

    void setExpert(boolean expert);

    Enumeration<String> attributeNames();

}