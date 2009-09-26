package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.bas.types.TextMap;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link PDBTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "imports", "tableOrViews" })
@XmlRootElement(name = "pdb")
public class PDB implements PDBElement {

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
    public static class Import implements PDBElement {

        @XmlAttribute(required = true)
        public String href;
        @XmlAttribute
        public String type;

        @Override
        public void accept(PDBVisitor visitor) {
            visitor.visit(this);
        }

    }

    @XmlElement(name = "import")
    protected List<PDB.Import> imports;

    @XmlElements( {
    //
            @XmlElement(name = "table", type = Table.class), //
            @XmlElement(name = "view", type = View.class) })
    protected List<Object>     tableOrViews;

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

    public List<Object> getTableOrViews() {
        if (tableOrViews == null)
            tableOrViews = new ArrayList<Object>();
        return tableOrViews;
    }

    public void addTable(Table table) {
        getTableOrViews().add(table);
    }

    public void addView(View view) {
        getTableOrViews().add(view);
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

    @Override
    public void accept(PDBVisitor visitor) {
        visitor.visit(this);
        if (imports != null)
            for (Import _import : imports)
                _import.accept(visitor);
        if (tableOrViews != null)
            for (Object o : tableOrViews)
                ((PDBElement) o).accept(visitor);
    }

    @XmlTransient
    TextMap<Object> tableOrViewMap;
    @XmlTransient
    TextMap<Table>  tableMap;
    @XmlTransient
    TextMap<View>   viewMap;

    public Object getTableOrView(String name) {
        return tableOrViewMap.get(name);
    }

    public Table getTable(String name) {
        return tableMap.get(name);
    }

    public View getView(String name) {
        return viewMap.get(name);
    }

}
