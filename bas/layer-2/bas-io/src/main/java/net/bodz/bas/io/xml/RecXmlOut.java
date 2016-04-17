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
public class RecXmlOut<self_t extends RecXmlOut<self_t>>
        implements IXmlOut {

    protected XmlDoc doc;
    protected transient ITreeOut treeOut;

    self_t parent;
    String tagName;
    private Map<String, String> attributes;

    private self_t pending;

    public static final int ATTRIBUTES = 0;
    public static final int TEXT = 1;
    public static final int END = 2;
    int state = TEXT;

    public RecXmlOut(XmlDoc doc) {
        this(doc.outputFormat);
        this.doc = doc;
        this.treeOut = doc.treeOut;
    }

    protected RecXmlOut(XmlOutputFormat outputFormat) {
        if (outputFormat == null)
            throw new NullPointerException("outputFormat");
        if (outputFormat.attributeBuffer) {
            if (outputFormat.sortAttributeNames == null)
                this.attributes = new HashMap<>();
            else if (outputFormat.sortAttributeNames)
                this.attributes = new TreeMap<>();
            else
                this.attributes = new LinkedHashMap<>();
        }
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public self_t getParent() {
        return parent;
    }

    @Override
    public final self_t begin(String name) {
        text();

        checkName(name);
        treeOut.print("<", name);

        pending = create();
        pending.parent = (self_t) this;
        pending.tagName = name;
        pending.state = ATTRIBUTES;
        return pending;
    }

    protected self_t create() {
        return (self_t) new RecXmlOut<>(doc);
    }

    @Override
    public final self_t end() {
        if (tagName == null)
            return null; // not applicable on document node.

        if (state != END) {
            if (state == ATTRIBUTES && doc.outputFormat.shortEmptyElement)
                treeOut.println(" />");
            else {
                text();
                treeOut.leave();
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
        if (state != ATTRIBUTES)
            throw new IllegalStateException("Attribute isn't allowed here.");

        checkName(name);

        if (doc.outputFormat.attributeBuffer) {
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
            String encoded = doc.outputFormat.encodeAttr(value);
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
        if (state == ATTRIBUTES) {
            if (doc.outputFormat.attributeBuffer) {
                for (Entry<String, String> entry : attributes.entrySet())
                    writeAttr(entry.getKey(), entry.getValue());
            }

            treeOut.print(">");
            treeOut.enter();
            state = TEXT;
        }
        if (pending != null) {
            pending.end();
            pending = null;
        }
        return (self_t) this;
    }

    @Override
    public self_t text(String str) {
        text();

        String encoded = doc.outputFormat.encodeText(str);
        treeOut.print(encoded);

        return (self_t) this;
    }

    @Override
    public self_t text(Object str) {
        return text(str == null ? null : str.toString());
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
    public self_t pi(String target, String data) {
        text();
        treeOut.print("<?" + target + ' ' + data + "?>");
        if (doc.outputFormat.newLineAfterPI)
            treeOut.println();
        return (self_t) this;
    }

    @Override
    public self_t comment(String str) {
        text();
        if (!doc.outputFormat.stripComment) {
            treeOut.print("<!-- " + str + " -->");
            if (doc.outputFormat.newLineAfterComment)
                treeOut.println();
        }
        return (self_t) this;
    }

    @Override
    public void verbatim(String str) {
        text();
        treeOut.print(str);
        if (doc.outputFormat.newLineAfterVerbatim)
            treeOut.println();
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
