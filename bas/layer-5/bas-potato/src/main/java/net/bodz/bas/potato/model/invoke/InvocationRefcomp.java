package net.bodz.bas.potato.model.invoke;

import java.util.Iterator;

import net.bodz.bas.c.java.util.PrefetchedIterator;
import net.bodz.bas.potato.ref.AbstractRefEntry;
import net.bodz.bas.potato.ref.IRefDescriptor;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.IRefcomp;

public class InvocationRefcomp
        extends AbstractRefEntry<Object>
        implements IRefcomp<Object> {

    private static final long serialVersionUID = 1L;

    private final Invocation invocation;
    private final InvocationDescriptor descriptor;

    public InvocationRefcomp(Invocation invocation, InvocationDescriptor descriptor) {
        super(descriptor.getName());
        this.invocation = invocation;
        this.descriptor = descriptor;
    }

    public Invocation getInvocation() {
        return invocation;
    }

    @Override
    public InvocationDescriptor getDescriptor() {
        return descriptor;
    }

    @Override
    public Class<?> getValueType() {
        return invocation.getReturnType();
    }

    @Override
    public Object get() {
        return invocation.getReturnValue();
    }

    @Override
    public void set(Object value) {
        invocation.setReturnValue(value);
    }

    // -o IRefEntryIndex

    @Override
    public int getRefEntryCount() {
        int n = descriptor.getParameterCount();
        if (descriptor.isNonVoid())
            n++;
        return n;
    }

    @Override
    public ParameterRef getRefEntry(int index) {
        return new ParameterRef(index);
    }

    @Override
    public IRefEntry<?> getRefEntry(String name) {
        if (name == null)
            throw new NullPointerException("name");

        IRefDescriptor[] descriptors = descriptor.getParameterDescriptors();
        int index = -1;
        for (int i = 0; i < descriptors.length; i++) {
            String paramName = descriptors[i].getName();
            if (name.equals(paramName)) {
                index = i;
                break;
            }
        }

        if (index != -1)
            return getRefEntry(index);
        else
            return null;
    }

    @Override
    public final Iterable<? extends IRefEntry<?>> getRefEntries() {
        return new Iterable<IRefEntry<?>>() {
            @Override
            public Iterator<IRefEntry<?>> iterator() {
                return new ParameterRefIterator();
            }
        };
    }

    private class ParameterRefIterator
            extends PrefetchedIterator<IRefEntry<?>> {

        int index = 0;

        @Override
        protected IRefEntry<?> fetch() {
            if (index < descriptor.getParameterCount()) {
                ParameterRef ref = getRefEntry(index++);
                return ref;
            } else {
                return end();
            }
        }

    }

    protected class ParameterRef
            extends AbstractRefEntry<Object> {

        private static final long serialVersionUID = 1L;

        private final int index;

        public ParameterRef(int index) {
            super(descriptor.getParameterDescriptor(index).getName());
            this.index = index;
        }

        @Override
        public IRefDescriptor getDescriptor() {
            IRefDescriptor paramDescriptor = descriptor.getParameterDescriptor(index);
            return paramDescriptor;
        }

        @Override
        public Class<? extends Object> getValueType() {
            return getDescriptor().getValueType();
        }

        @Override
        public Object get() {
            return invocation.getParameter(index);
        }

        @Override
        public void set(Object value) {
            invocation.setParameter(index, value);
        }

    }

}
