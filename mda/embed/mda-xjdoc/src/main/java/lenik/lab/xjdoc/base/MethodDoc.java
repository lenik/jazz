package lenik.lab.xjdoc.base;

import java.util.Map;

import lenik.lab.xjdoc.dstr.DomainString;

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
