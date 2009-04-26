package net.bodz.swt.gui.pfl;

import java.io.StringReader;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.swt.nls.GUINLS;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @test {@link SymlinkPageFlowTest}
 */
public class SymlinkPageFlow extends PageFlow {

    public static final int maxDepth = 16;

    private TextMap<Object> symlinks;

    public SymlinkPageFlow() {
        symlinks = new TreeTextMap<Object>();
    }

    @Override
    public String normalize(String address) {
        if (address == null)
            return null;
        int depth = 0;
        do {
            // don't expand symlink if the address is already defined.
            if (isPageLoaded(address))
                return address;
            if (isPageLoadable(address))
                return address;
            Object _symlink = symlinks.get(address);
            String symlink = _symlink == null ? null : String.valueOf(_symlink);
            if (symlink != null) {
                address = symlink;
                continue;
            }
            return address;
        } while (++depth < maxDepth);
        throw new IllegalStateException(GUINLS.getString("SymlinkPageFlow.exceedsSymDepth") //$NON-NLS-1$
                + depth);
    }

    public Object getLink(String name) {
        return symlinks.get(name);
    }

    /**
     * @param name
     *            address of the link itself.
     * @param target
     *            target address, its {@link Object#toString() toString} method
     *            is evaluated to get the real-time target address.
     * @see #getLink(String) Get the link itself.
     * @see Location#resolv(String) Resolve the link.
     */
    public void putLink(String name, Object target) {
        symlinks.put(name, target);
    }

    public void putLink(String name, String next, Object target) {
        String joined = _resolv(name, next);
        putLink(joined, target);
    }

    public void removeLink(String name) {
        symlinks.remove(name);
    }

    public String dumpXML() {
        BCharOut buf = new BCharOut(symlinks.size() * 30);
        buf.println("<symlinks>"); //$NON-NLS-1$
        for (Entry<String, Object> e : symlinks.entrySet()) {
            String name = e.getKey();
            Object dest = e.getValue();
            buf.printf("<symlink name=\"%s\" dest=\"%s\" />\n", name, dest); //$NON-NLS-1$
        }
        buf.println("</symlinks>"); //$NON-NLS-1$
        return buf.toString();
    }

    public void loadXML(String s) throws ParseException {
        class Handler extends DefaultHandler {
            @Override
            public void startElement(String uri, String localName, String qName,
                    Attributes attributes) throws SAXException {
                if (!"symlink".equals(qName)) //$NON-NLS-1$
                    return;
                String name = attributes.getValue("name"); //$NON-NLS-1$
                String dest = attributes.getValue("dest"); //$NON-NLS-1$
                putLink(name, dest);
            }
        }
        SAXParserFactory saxf = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxf.newSAXParser();
            StringReader src = new StringReader(s);
            InputSource in = new InputSource(src);
            parser.parse(in, new Handler());
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @Override
    public String toString() {
        return dumpXML();
    }

}
