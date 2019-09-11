package net.bodz.bas.io.xml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@SuppressWarnings("unchecked")
@ThreadUnsafe
public abstract class AbstractRecXmlOut<node_t extends AbstractRecXmlOut<node_t, ?>, self_t extends AbstractRecXmlOut<node_t, self_t>>
        implements IXmlOut {

    protected final XmlDoc doc;
    protected final ITreeOut treeOut;

    node_t parent;
    String tagName;
    private Map<String, String> attributes;

    private node_t pending;

    public static final int START_TAG = 0;
    public static final int TEXT = 1;
    public static final int END = 2;
    int state = TEXT;

    public AbstractRecXmlOut(XmlDoc doc) {
        this.doc = doc;
        this.treeOut = doc.getTreeOut();

        XmlOutputFormat outputFormat = doc.getOutputFormat();
        if (outputFormat == null)
            throw new NullPointerException("outputFormat");
        if (outputFormat.attributeBuffer) {
            if (outputFormat.sortAttributeNames == null)
                this.attributes = new HashMap<String, String>();
            else if (outputFormat.sortAttributeNames)
                this.attributes = new TreeMap<String, String>();
            else
                this.attributes = new LinkedHashMap<String, String>();
        }
    }

    @Override
    public XmlDoc getDoc() {
        return doc;
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public node_t getParent() {
        return parent;
    }

    @Override
    public self_t dtd(String tag, String data) {
        text();
        treeOut.print("<!" + tag + ' ' + data + ">");
        if (doc.getOutputFormat().newLineAfterDtd)
            treeOut.println();
        return (self_t) this;
    }

    @Override
    public self_t pi(String target, String data) {
        text();
        treeOut.print("<?" + target + ' ' + data + "?>");
        if (doc.getOutputFormat().newLineAfterPI)
            treeOut.println();
        return (self_t) this;
    }

    @Override
    public abstract node_t begin(String name);

    protected <T extends node_t> T begin(String name, T node) {
        text();

        checkName(name);

        treeOut.print("<", name);

        pending = node;
        pending.parent = (node_t) this;
        pending.tagName = name;
        pending.state = START_TAG;
        return node;
    }

    protected void startTagClose() {
        treeOut.print(">");
        treeOut.enter();
        if (doc.getOutputFormat().newLineAfterStartTag)
            treeOut.println();
    }

    @Override
    public node_t end() {
        if (tagName == null)
            return null; // not applicable on document node.

        if (state != END) {
            if (state == START_TAG && doc.getOutputFormat().shortEmptyElement)
                treeOut.println(" />");
            else {
                text();

                treeOut.leave();
                if (doc.getOutputFormat().newLineBeforeEndTag)
                    treeOut.println();

                treeOut.println("</" + tagName + ">");
            }
            state = END;
        }
        return parent;
    }

    @Override
    public self_t attrs(Map<String, ?> attributes) {
        for (Entry<String, ?> entry : attributes.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            // if (value != null)
            attr(name, value);
        }
        return (self_t) this;
    }

    @Override
    public self_t attr(String name, Object value) {
        if (value != null)
            attr(name, value.toString());
        return (self_t) this;
    }

    @Override
    public self_t attr(String name, String value) {
        if (state != START_TAG)
            throw new IllegalStateException("Attribute isn't allowed here.");

        checkName(name);

        if (doc.getOutputFormat().attributeBuffer) {
            attributes.put(name, value);
        } else {
            writeAttr(name, value);
        }
        return (self_t) this;
    }

    void writeAttr(String name, String value) {
        if (value != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(' ');
            sb.append(name);
            sb.append("=\"");
            String encoded = doc.getOutputFormat().encodeAttr(value);
            sb.append(encoded);
            sb.append('\"');
            treeOut.print(sb);
        }
    }

    @Override
    public self_t id(String id) {
        return attr("id", id);
    }

    self_t text() {
        // if (state == END) ;
        // just like calling parent.text().
        if (state == START_TAG) {
            if (doc.getOutputFormat().attributeBuffer) {
                for (Entry<String, String> entry : attributes.entrySet())
                    writeAttr(entry.getKey(), entry.getValue());
            }

            startTagClose();

            state = TEXT;
        }

        if (pending != null) {
            node_t node = pending;
            pending = null;
            node.end();
        }
        return (self_t) this;
    }

    @Override
    public self_t text(String str) {
        text();

        if (str == null)
            str = doc.getOutputFormat().nullText;

        if (str != null) {
            String encoded = doc.getOutputFormat().encodeText(str);
            treeOut.print(encoded);
        }

        return (self_t) this;
    }

    @Override
    public self_t text(Object str) {
        return text(str == null ? null : str.toString());
    }

    @Override
    public self_t textf(String fmt, Object... args) {
        String s = String.format(fmt, args);
        return text(s);
    }

    @Override
    public final self_t textln(String str) {
        text(str);
        treeOut.println();
        return (self_t) this;
    }

    @Override
    public self_t textln(Object str) {
        text(str);
        treeOut.println();
        return (self_t) this;
    }

    @Override
    public final self_t cdata(String cdata) {
        text();

        int start = 0;
        int len = cdata.length();
        while (start < len) {
            int brk = cdata.indexOf("]]>", start);
            int end = brk == -1 ? len : brk;

            treeOut.print("<![CDATA[");
            treeOut.print(cdata.substring(start, end));
            treeOut.print("]]>");

            if (brk != -1) {
                treeOut.print("]]&gt;");
                end += 3;
            }

            start = end;
        }
        return (self_t) this;
    }

    @Override
    public self_t comment(String str) {
        text();
        if (!doc.getOutputFormat().stripComment) {
            treeOut.print("<!-- " + str + " -->");
            if (doc.getOutputFormat().newLineAfterComment)
                treeOut.println();
        }
        return (self_t) this;
    }

    @Override
    public void verbatim(String str) {
        text();
        treeOut.print(str);
        if (doc.getOutputFormat().newLineAfterVerbatim)
            treeOut.println();
    }

    @Override
    public void indent(int level) {
        while (level > 0) {
            treeOut.enter();
            level--;
        }
        while (level < 0) {
            treeOut.leave();
            level++;
        }
    }

    @Override
    public boolean isClosed() {
        return treeOut.isClosed();
    }

    @Override
    public void flush(boolean sync) {
        text();
        treeOut.flush(sync);
    }

    @Override
    public void flush() {
        text();
        treeOut.flush();
    }

    @Override
    public void close() {
        text();
        end();
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
            } else if (!letter)
                switch (ch) {
                case '-':
                case '.':
                case ':':
                    break;
                default:
                    throw new IllegalArgumentException("Illegal char in the name: " + ch);
                }
        }
    }

}
