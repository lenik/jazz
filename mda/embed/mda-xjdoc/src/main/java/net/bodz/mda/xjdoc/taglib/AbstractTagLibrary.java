package net.bodz.mda.xjdoc.taglib;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Injector;
import net.bodz.bas.t.preorder.PackageMap;
import net.bodz.mda.xjdoc.model.IDocTag;

public abstract class AbstractTagLibrary
        implements
            ITagLibrary {

    final int priority;
    PackageMap<Class<? extends IDocTag<?>>> map = new PackageMap<>();
    Class<? extends IDocTag<?>> defaultTagType;

    public AbstractTagLibrary() {
        this(0);
    }

    public AbstractTagLibrary(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getName() {
        String name = getClass().getSimpleName();
        name = StringPart.rtrim(name, "TagLibrary");
        name = Strings.lcfirst(name);
        return name;
    }

    @Override
    public String getRootTagName(String tagName) {
        String rootTagName = map.meetKey(tagName);
        if (rootTagName != null)
            return rootTagName;
        if (defaultTagType != null)
            return tagName;
        return null;
    }

    public Class<? extends IDocTag<?>> getDefaultTagType() {
        return defaultTagType;
    }

    public void setDefaultTagType(Class<? extends IDocTag<?>> defaultTagType) {
        this.defaultTagType = defaultTagType;
    }

    public void addTagType(String rootTagName, Class<? extends IDocTag<?>> type) {
        if (rootTagName == null)
            throw new NullPointerException("rootTagName");

        Class<? extends IDocTag<?>> existing = map.get(rootTagName);
        if (existing != null)
            throw new DuplicatedKeyException(rootTagName, existing);

        map.put(rootTagName, type);
    }

    @Override
    public IDocTag<?> createTag(String rootTagName) {
        Class<? extends IDocTag<?>> type = map.get(rootTagName);
        if (type == null)
            type = defaultTagType;
        if (type != null) {
            Injector injector = new Injector();
            injector.setExplicitConstructor(true); // require single constructor
            injector.setRequireAll(true); // require all parameters specified
            try {
                return injector.instantiate(type);
            } catch (ReflectiveOperationException e) {
                throw new CreateException(e.getMessage(), e);
            }
        }
        return null;
    }

    public static ITagLibrary fromOptions(IOptions options) {
        if (options == null)
            throw new NullPointerException("options");

        ITagLibrary instance = options.get(ITagLibrary.class);
        if (instance == null)
            throw new IllegalUsageException("Tag library isn't provided in options.");

        return instance;
    }

}
