package net.bodz.mda.xjdoc.base;

import java.util.Map;

import net.bodz.mda.xjdoc.dstr.DomainString;


public class MethodDoc
        extends ElementDoc {

    Map<String, DomainString> parameters;
    DomainString returnValue;
    Map<String, DomainString> exceptions;

    public MethodDoc() {
        super();
    }

    public MethodDoc(String name) {
        super(name);
    }

}
