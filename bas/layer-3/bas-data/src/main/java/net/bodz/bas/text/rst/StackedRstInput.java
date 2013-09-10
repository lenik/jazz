package net.bodz.bas.text.rst;

import java.io.IOException;
import java.util.LinkedList;

import net.bodz.bas.err.ParseException;

public class StackedRstInput
        implements IRstInput {

    private IRstInput in;
    private LinkedList<RstElement> stack;

    public StackedRstInput(IRstInput in) {
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
        this.stack = new LinkedList<RstElement>();
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
        RstElement element;

        switch (ret = in.next()) {
        case ELEMENT_BEGIN:
            element = new RstElement(in.getElementName(), in.getElementArguments());
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
        IRstElement element = stack.peek();
        if (element == null)
            return null;
        else
            return element.getName();
    }

    @Override
    public String[] getElementArguments() {
        IRstElement element = stack.peek();
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
