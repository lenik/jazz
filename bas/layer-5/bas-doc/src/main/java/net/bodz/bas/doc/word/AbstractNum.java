package net.bodz.bas.doc.word;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

/**
 * Also, num-style.
 */
public class AbstractNum {

    static final Logger logger = LoggerFactory.getLogger(AbstractNum.class);

    static Map<String, CTAbstractNum> nameMap = new HashMap<>();

    public static CTAbstractNum getNumStyle(String name) {
        return nameMap.get(name);
    }

    static {
        loadXmls();
    }

    static void loadXmls() {
        URLResource listFile = ClassResource.getData(AbstractNum.class, "list");
        for (String name : listFile.read().lines()) {
            name = name.trim();
            URL url = AbstractNum.class.getResource("AbstractNum/" + name);
            if (url == null)
                throw new LoadException("no xml resource for name: " + name);
            String xml;
            try {
                xml = new URLResource(url).read().readString();
            } catch (IOException e) {
                String msg = String.format("failed to read xml %s: %s", url, e.getMessage());
                throw new LoadException(msg, e);
            }

            CTNumbering cTNumbering;
            try {
                cTNumbering = CTNumbering.Factory.parse(xml);
            } catch (XmlException e) {
                String msg = String.format("failed to parse xml %s: %s", url, e.getMessage());
                throw new LoadException(msg, e);
            }

            CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);

            synchronized (nameMap) {
                nameMap.put(name, cTAbstractNum);
            }
        }
    }

    public static CTAbstractNum parse(String xml)
            throws XmlException {
        CTNumbering cTNumbering;
        cTNumbering = CTNumbering.Factory.parse(xml);

        CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);
        return cTAbstractNum;
    }

    public static CTAbstractNum register(String name, String xml)
            throws XmlException {
        CTAbstractNum numStyle = parse(xml);
        return register(name, numStyle);
    }

    public static CTAbstractNum register(String name, CTAbstractNum numStyle) {
        synchronized (nameMap) {
            CTAbstractNum prev = nameMap.get(name);
            if (prev != null)
                throw new DuplicatedKeyException(name);
            nameMap.put(name, numStyle);
            return numStyle;
        }
    }

}
