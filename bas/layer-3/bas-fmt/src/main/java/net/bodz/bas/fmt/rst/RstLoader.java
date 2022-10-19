package net.bodz.bas.fmt.rst;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.io.res.IStreamInputSource;

class Frame {

    IRstHandler handler;
    RstElement save;

    public Frame(String elementName, String[] args) {
        this(elementName, args, null);
    }

    public Frame(String elementName, String[] args, IRstHandler handler) {
        this.save = new RstElement(elementName, args);
        if (handler == null)
            handler = new RstElement(elementName, args);
        this.handler = handler;
    }

}

public class RstLoader {

    public void load(IStreamInputSource inputSource, IRstHandler handler)
            throws IOException, ParseException, ElementHandlerException {
        Reader reader = inputSource.newReader();
        IRstInput in = null;
        try {
            in = new RstInput(reader);
            load(in, handler);
        } finally {
            if (in != null)
                in.close();
            reader.close();
        }
    }

    public void load(IRstInput in, IRstHandler handler)
            throws IOException, ParseException, ElementHandlerException {
        int token;

        LinkedList<Frame> stack = new LinkedList<Frame>();
        Frame frame = new Frame(null, IEmptyConsts.emptyStringArray, handler);
        stack.push(frame);

        do {
            switch (token = in.next()) {
            case IRstInput.ATTRIBUTE:
                String attributeName = in.getAttributeName();
                String attributeData = in.getAttributeData();
                if (attributeData == null)
                    throw new NullPointerException("attributeData");
                if (!handler.attribute(attributeName, attributeData)) {
                    frame.save.attribute(attributeName, attributeData);
                }
                break;

            case IRstInput.ELEMENT_BEGIN:
                String elementName = in.getElementName();
                String[] elementArgs = in.getElementArguments();

                handler = handler.beginChild(elementName, elementArgs);
                if (handler == null)
                    handler = frame.save.beginChild(elementName, elementArgs);
                frame = new Frame(elementName, elementArgs, handler);
                stack.push(frame);
                break;

            case IRstInput.ELEMENT_END:
                if (stack.size() <= 1)
                    throw new ParseException("Too many '}'s, outside of the document.");

            case IRstInput.EOF:
                IRstElement savedElement = frame.save;
                handler.complete(savedElement);
                stack.pop();

                if (stack.isEmpty()) {
                    frame = null;
                    handler = null;
                } else {
                    frame = stack.peek();
                    handler = frame.handler;
                    if (!handler.endChild(savedElement))
                        frame.save.endChild(savedElement);
                }
                break;

            default:
                throw new UnexpectedException();
            }
        } while (token != IRstInput.EOF);
    }

}
