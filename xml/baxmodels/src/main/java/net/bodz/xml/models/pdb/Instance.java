package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link InstanceTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "any" })
@XmlRootElement(name = "instance", namespace = Instance.NS)
public class Instance implements PDBElement {

    public static final String NS = "http://xml.bodz.net/schema/pdb";

    @XmlAttribute
    protected String           name;
    @XmlAttribute
    protected String           label;
    @XmlAttribute
    protected String           tags;
    @XmlAttribute
    protected String           doc;

    @XmlTransient
    protected List<Object>     any;

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

    @XmlAnyElement(lax = true)
    public List<Object> getAny() {
        if (any == null) {
            if (rowData == null)
                any = new ArrayList<Object>();
            else {
                assert table != null : "collect mis-order";
                // Reconstruct the xml element from RowData.
                List<Field> fields = table.getFields();
                int n = fields.size();
                any = new ArrayList<Object>(n); // n at max.
                for (int i = 0; i < n; i++) {
                    String value = rowData.get(i);
                    if (value != null) {
                        // construct <name>value</name>
                        String fieldName = fields.get(i).getName();
                        QName qname = new QName(Instance.NS, fieldName);
                        JAXBElement<String> elm = new JAXBElement<String>(qname, String.class,
                                value);
                        any.add(elm);
                    }
                }
            }
        }
        return this.any;
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

    @XmlTransient
    Table           table;
    @XmlTransient
    private RowData rowData;

    public RowData getRowData() {
        return rowData;
    }

    public void setRowData(RowData rowData) {
        this.rowData = rowData;
        this.any = null;
    }

}
