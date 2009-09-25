package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link PDBTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "imports", "tables", "views" })
@XmlRootElement(name = "pdb")
public class PDB {

    @XmlAttribute(required = true)
    protected String name;
    @XmlAttribute
    protected String qname;
    @XmlAttribute
    protected String cat;
    @XmlAttribute
    protected String label;
    @XmlAttribute
    protected String tags;
    @XmlAttribute
    protected String doc;

    @XmlTransient
    private boolean  extern;

    // TODO: name=import
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType
    public static class Import {
        @XmlAttribute(required = true)
        public String href;
        @XmlAttribute
        public String type;
    }

    @XmlElement(name = "import")
    protected List<PDB.Import> imports;

    @XmlElement(name = "table", type = Table.class)
    protected List<Table>      tables;

    @XmlElement(name = "view", type = View.class)
    protected List<View>       views;

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

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

    public List<PDB.Import> getImports() {
        if (imports == null)
            imports = new ArrayList<Import>();
        return imports;
    }

    public void addImport(String type, String href) {
        if (imports == null)
            imports = new ArrayList<Import>();
        Import _import = new Import();
        _import.type = type;
        _import.href = href;
        imports.add(_import);
    }

    public List<Table> getTables() {
        if (tables == null)
            tables = new ArrayList<Table>();
        return tables;
    }

    public List<View> getViews() {
        if (views == null)
            views = new ArrayList<View>();
        return views;
    }

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        if (extern)
            b.put(OPT_EXTERN);
        return b.toString();
    }

    public void setOpts(String opts) {
        extern = false;
        for (Term t : TermParser.parse(__dict__, opts))
            switch (t.getId()) {
            case OPT_EXTERN:
                extern = true;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
    }

    public boolean isExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }

    static final int      OPT_EXTERN = 1;
    static final TermDict __dict__   = new TermDict();
    static {
        __dict__.define(OPT_EXTERN, "E");
    }

}
