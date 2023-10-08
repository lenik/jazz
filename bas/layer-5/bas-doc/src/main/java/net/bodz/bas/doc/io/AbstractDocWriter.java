package net.bodz.bas.doc.io;

import java.util.Stack;

import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.PartLevel;

public abstract class AbstractDocWriter<self_t extends AbstractDocWriter<self_t>>
        implements
            IDocWriter<self_t> {

    Stack<ElementType> stack = new Stack<>();

    protected void push(ElementType elementType) {
        stack.push(elementType);
    }

    protected ElementType pop() {
        return stack.pop();
    }

    @SuppressWarnings("unchecked")
    protected final self_t self() {
        return (self_t) this;
    }

    @Override
    public self_t begin(String className) {
        push(ElementType.BLOCK);
        return self();
    }

    @Override
    public self_t end() {
        pop();
        return self();
    }

    @Override
    public self_t end(ElementType elementType) {
        synchronized (stack) {
            ElementType top = stack.peek();
            if (top != elementType) {
                String message = String.format("current element %s is not the expected %s.", //
                        top, elementType);
                throw new IllegalStateException(message);
            }
            stack.pop();
        }
        return self();
    }

    @Override
    public void endAll() {
        stack.clear();
    }

    @Override
    public void flush() {
    }

    @Override
    public self_t attribute(String name, Object value) {
        return self();
    }

    @Override
    public self_t put(Object element) {
        return self();
    }

    @Override
    public self_t section(PartLevel level, String title) {
        stack.push(level.elementType);
        return self();
    }

}
