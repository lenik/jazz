package net.bodz.bas.repr.pointer;

public class ClassResourcePointer
        extends AbstractResourcePointer {

    private final Class<?> contextClass;
    private final String href;

    public ClassResourcePointer(Class<?> contextClass, String href) {
        if (contextClass == null)
            throw new NullPointerException("contextClass");
        if (href == null)
            throw new NullPointerException("href");
        this.contextClass = contextClass;
        this.href = href;
    }

    @Override
    public String instantiate(String context) {

        return null;
    }

}
