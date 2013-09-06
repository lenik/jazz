package net.bodz.bas.text.structf;

import java.io.IOException;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;

public class StackedStructfInput
        implements IStructfInput {

    private IStructfInput in;
    private LinkedList<StructfElement> stack;

    public StackedStructfInput(IStructfInput in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
        this.stack = new LinkedList<StructfElement>();
    }

    @Override
    public void close()
            throws IOException {
        in.close();
    }

    @Override
    public int next()
            throws ParseException, IOException {
        int ret;
        StructfElement element;

        switch (ret = in.next()) {
        case ELEMENT_BEGIN:
            element = new StructfElement(in.getElementName(), in.getElementArguments());
            stack.push(element);
            break;

        case ELEMENT_END:
            stack.pop();
            break;

        case ATTRIBUTE:
            element = stack.peek(); // = first.
            if (element == null)
                throw new IllegalStateException("Attribute outside of any element.");
            element.attribute(in.getAttributeName(), in.getAttributeData());
            break;

        case EOF:
        }
        return ret;
    }

    @Override
    public String getElementName() {
        IStructfElement element = stack.peek();
        if (element == null)
            return null;
        else
            return element.getName();
    }

    @Override
    public String[] getElementArguments() {
        IStructfElement element = stack.peek();
        if (element == null)
            return null;
        else
            return element.getArguments();
    }

    @Override
    public String getAttributeName() {
        return in.getAttributeName();
    }

    @Override
    public String getAttributeData() {
        return in.getAttributeData();
    }

}
