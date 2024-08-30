package net.bodz.mda.xjdoc.model;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.coll.ContainerType;
import net.bodz.bas.t.coll.IContainer;

public abstract class MultiDocTag<E>
        extends AbstractDocTag<IContainer<E>>
        {

    protected final IContainer<E> container;

    public MultiDocTag(ContainerType containerType, Class<? extends E> valueType) {
        this(containerType.create(valueType));
    }

    public MultiDocTag(IContainer<E> container) {
        if (container == null)
            throw new NullPointerException("container");
        this.container = container;
    }

    @Override
    public IContainer<E> getContainer() {
        return container;
    }

    @Override
    public E toScalar() {
        if (container.isEmpty())
            return null;
        else
            return container.getFirst();
    }

    @Override
    public Object at(int index) {
        return null;
    }

    @Override
    public Object at(int index, Object fallback) {
        return null;
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String string, IOptions options)
            throws ParseException {
        IContainer<E> c = getContainer();

        int index = Integer.parseInt(extension);
        while (c.size() < index)
            c.add(null);

        // Object valueCont = list.get(index);
        E value = parseItem(string, options);
        if (c.size() == index)
            c.add(value);
        else
            c.set(index, value);
    }

    @Override
    public void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException,
            FormatException {
        IContainer<E> c = getContainer();
        int index = 0;
        for (E item : c) {
            if (item != null) {
                writeItem(out, attributeName + "." + index, item, options);
            }
            index++;
        }
    }

    protected abstract E parseItem(String string, IOptions options)
            throws ParseException;

    protected abstract void writeItem(IFlatfOutput out, String name, E item, IOptions options)
            throws IOException,
            FormatException;

}
