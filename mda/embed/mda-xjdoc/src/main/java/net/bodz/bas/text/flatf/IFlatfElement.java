package net.bodz.bas.text.flatf;

import java.util.Collection;

public interface IFlatfElement {

    String getName();

    Collection<IFlatfAttribute> getAttributes();

    int getAttributeCount();

    String getAttributeName(int index);

    String getAttributeText(int index);

    void addAttribute(String name, String text);

}