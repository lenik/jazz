package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

@Deprecated
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "field" })
public class Index {

    @XmlAttribute
    protected String       name;
    @XmlAttribute
    protected String       label;
    @XmlAttribute
    protected String       tags;
    @XmlAttribute
    protected String       doc;

    @XmlElement(required = true)
    protected List<String> field;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public List<String> getField() {
        if (field == null) {
            field = new ArrayList<String>();
        }
        return this.field;
    }

    public static final int DICT_CACHED = 1;
    private int             dictMode;

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        switch (dictMode) {
        case DICT_CACHED:
            b.put(OPT_DICT_CACHED);
            break;
        }
        return b.toString();
    }

    public void setOpts(String value) {
        dictMode = 0;
        for (Term t : TermParser.parse(__dict__, value)) {
            switch (t.getId()) {
            case OPT_DICT_CACHED:
                dictMode = DICT_CACHED;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
        }
    }

    static final int      OPT_DICT_CACHED = 1;
    static final TermDict __dict__        = new TermDict();
    static {
        __dict__.define(OPT_DICT_CACHED, "D");
    }

}
