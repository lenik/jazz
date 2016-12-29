package net.bodz.swt.c.pageflow;

import java.util.Collection;

public class PageEditor {

    public static void setMethods(AbstractPage page, Collection<PageMethod> methods) {
        page.setMethods(methods);
    }

    public static void setMethods(AbstractPage page, PageMethod... methods) {
        page.setMethods(methods);
    }

    public static void addMethod(AbstractPage page, PageMethod method) {
        page.addMethod(method);
    }

    public static void removeMethod(AbstractPage page, PageMethod method) {
        page.removeMethod(method);
    }

}
