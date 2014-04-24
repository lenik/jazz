package net.bodz.bas.io.xml;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.io.AbstractTreeOut;
import net.bodz.bas.io.ITextIndention;
import net.bodz.bas.io.PrintException;
import net.bodz.bas.io.TextIndention;
import net.bodz.bas.meta.decl.ThreadUnsafe;
import net.bodz.bas.t.list.ArrayStack;
import net.bodz.bas.t.list.Stack;

@ThreadUnsafe
public abstract class AbstractXmlOut
        extends AbstractTreeOut
        implements IXmlOut {

    private XmlOutputFormat outputFormat;
    private Stack<String> tagStack = new ArrayStack<>();

    private boolean startTagMalformed;

    private XmlOutTagBuffer pending;

    private XmlStringEncoder attribEncoder = XmlStringEncoder.forAttribute(this);
    private XmlStringEncoder textEncoder = XmlStringEncoder.forText(this);

    public AbstractXmlOut() {
        this(new XmlOutputFormat());
    }

    public AbstractXmlOut(XmlOutputFormat outputFormat) {
        this(outputFormat, new TextIndention(outputFormat.getIndentSize(), 8));
    }

    AbstractXmlOut(XmlOutputFormat outputFormat, ITextIndention textIndention) {
        super(textIndention);
        if (outputFormat == null)
            throw new NullPointerException("outputFormat");
        this.outputFormat = outputFormat;
    }

    @Override
    public void processInstruction(String target, String data) {
        ensureTextState();
        print("<?" + target + ' ' + data + "?>");
    }

    @Override
    public void comment(String str) {
        if (outputFormat.isStripComment())
            return;
        ensureTextState();
        println("<!-- " + str + " -->");
    }

    @Override
    public IXmlTagBuilder tag(String name) {
        return tag(name, null, null);
    }

    @Override
    public final IXmlTagBuilder tag(String name, String text) {
        return tag(name, null, text);
    }

    @Override
    public IXmlTagBuilder tag(String name, Map<String, ?> attributes, String text) {
        ensureTextState();

        checkName(name);
        print("<", name);

        if (attributes != null)
            attributes(attributes);

        enter();
        tagStack.push(name);

        pending = new XmlOutTagBuffer(this);
        pending.text = text;

        return pending;
    }

    @Override
    public final IXmlTagBuilder start(String name) {
        return start(name, null);
    }

    @Override
    public IXmlTagBuilder start(String name, Map<String, ?> attributes) {
        ensureTextState();

        checkName(name);
        print("<", name);

        if (attributes != null)
            attributes(attributes);

        enter();
        tagStack.push(name);

        pending = new XmlOutTagBuffer(this);
        pending.start();

        return pending;
    }

    private void _endTag() {
        if (tagStack.isEmpty())
            throw new IllegalStateException("Tag stack empty.");

        leave();

        String name = tagStack.pop();
        println("</" + name + ">");
    }

    @Override
    public void end() {
        ensureTextState();
        _endTag();
    }

    @Override
    public void endAll() {
        ensureTextState();
        while (!tagStack.isEmpty())
            _endTag();
    }

    @Override
    protected void _close()
            throws IOException {
        endAll();
    }

    @Override
    public void startTagBegin(String name) {
        ensureTextState();

        print("<" + name);
        startTagMalformed = true;

        tagStack.push(name);
    }

    @Override
    public void startTagEnd(boolean empty) {
        if (!startTagMalformed)
            throw new IllegalStateException("No malformed start tag.");
        if (empty) {
            print("/>");
            tagStack.pop();
        } else {
            print(">");
        }
        startTagMalformed = false;
    }

    final void attributes(Map<String, ?> attributes) {
        for (Entry<String, ?> entry : attributes.entrySet()) {
            String aName = entry.getKey();
            Object aValue = entry.getValue();
            if (aValue == null)
                continue;
            attribute(aName, aValue);
        }
    }

    @Override
    public void attribute(String name, Object value) {
        checkName(name);

        String valStr = value.toString();

        print(' ' + name + "=\"");
        try {
            attribEncoder.encode(valStr);
        } catch (IOException e) {
            throw new PrintException(e.getMessage(), e);
        }
        print('\"');
    }

    @Override
    public void text(String str) {
        ensureTextState();
        _text(str);
    }

    private void _text(String str) {
        try {
            textEncoder.encode(str);
        } catch (IOException e) {
            throw new PrintException(e.getMessage(), e);
        }
    }

    @Override
    public final void textln(String str) {
        text(str);
        println();
    }

    @Override
    public final void cdata(String cdata) {
        ensureTextState();

        int start = 0;
        int len = cdata.length();
        while (start < len) {
            int brk = cdata.indexOf("]]>", start);
            int end = brk == -1 ? len : (brk + 2);

            print("<![CDATA[");
            print(cdata.substring(start, end));
            print("]]>");

            start = end;
        }
    }

    void ensureTextState() {
        if (startTagMalformed)
            throw new IllegalStateException("Start tag malformed.");

        if (pending != null) {
            print('>');
            if (pending.text != null)
                _text(pending.text);

            int exit = pending.getExit();
            for (int i = 0; i < exit; i++)
                _endTag();

            pending = null;
        }
    }

    void checkName(String name) {
        int len = name.length();
        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            boolean letter = Character.isLetter(ch);
            boolean digit = Character.isDigit(ch);
            if (i == 0) {
                if (!letter)
                    throw new IllegalArgumentException("Name must start with a letter.");
            } else if (i == len - 1) {
                if (!(letter || digit))
                    throw new IllegalArgumentException("Name must end with a letter or digit.");
            } else {
                boolean dashDot = ch == '-' || ch == '.';
                if (!(letter || digit || dashDot))
                    throw new IllegalArgumentException("Illegal char in the name: " + ch);
            }
        }
    }

}
