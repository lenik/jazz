package net.bodz.xml.models.pdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Trigger implements PDBElement {

    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected String label;
    @XmlAttribute
    protected String tags;
    @XmlAttribute
    protected String doc;

    @XmlElement(required = true)
    protected String sql;

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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public static final int RESERVED = 1;
    @XmlTransient
    private boolean         reserved;

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        if (reserved)
            b.put(OPT_RESERVED);
        return b.toString();
    }

    public void setOpts(String value) {
        reserved = false;
        for (Term t : TermParser.parse(__dict__, value)) {
            switch (t.getId()) {
            case OPT_RESERVED:
                reserved = true;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
        }
    }

    static final int      OPT_RESERVED = 1;
    static final TermDict __dict__     = new TermDict();
    static {
        __dict__.define(OPT_RESERVED, "R");
    }

    @Override
    public void accept(PDBVisitor visitor) {
        visitor.visit(this);
    }

}
