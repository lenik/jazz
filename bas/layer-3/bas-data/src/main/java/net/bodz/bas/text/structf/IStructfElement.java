package net.bodz.bas.text.structf;

import java.util.List;

public interface IStructfElement {

    String getName();

    String[] getArguments();

    List<IStructfAttribute> getAttributes();

    List<IStructfElement> getChildren();

}
