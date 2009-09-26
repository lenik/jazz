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

import net.bodz.bas.types.TextMap;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link TableTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "fields", "indexes", "checks", "triggers", "instances" })
@XmlRootElement(name = "table")
public class Table implements PDBElement {

    @XmlAttribute(required = true)
    protected String         base;
    @XmlAttribute(required = true)
    protected String         name;
    @XmlAttribute
    protected String         label;
    @XmlAttribute
    protected String         tags;
    @XmlAttribute
    protected String         doc;

    @XmlElement(name = "field", required = true)
    protected List<Field>    fields;
    @XmlElement(name = "index")
    protected List<Index>    indexes;
    @XmlElement(name = "check")
    protected List<Check>    checks;
    @XmlElement(name = "trigger")
    protected List<Trigger>  triggers;
    @XmlElement(name = "instance", namespace = Instance.NS)
    protected List<Instance> instances;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
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

    public List<Field> getFields() {
        if (fields == null) {
            fields = new ArrayList<Field>();
        }
        return this.fields;
    }

    public List<Index> getIndexes() {
        if (indexes == null) {
            indexes = new ArrayList<Index>();
        }
        return this.indexes;
    }

    public List<Check> getChecks() {
        if (checks == null) {
            checks = new ArrayList<Check>();
        }
        return this.checks;
    }

    public List<Trigger> getTriggers() {
        if (triggers == null) {
            triggers = new ArrayList<Trigger>();
        }
        return this.triggers;
    }

    public List<Instance> getInstances() {
        if (instances == null) {
            instances = new ArrayList<Instance>();
        }
        return this.instances;
    }

    @XmlTransient
    private boolean         _abstract;
    public static final int CACHE_DICT          = 1;
    public static final int CACHE_REDUNDANT     = 2;
    public static final int CACHE_TRANSIENT     = 3;

    @XmlTransient
    private int             cacheStrategy;

    @XmlTransient
    private boolean         readOnly;

    public static final int MAINTABLE           = 0;
    public static final int SUBTABLE_GENERAL    = 1;
    public static final int SUBTABLE_MOREFIELDS = 2;
    public static final int SUBTABLE_PROPERTIES = 3;
    public static final int SUBTABLE_DETAILS    = 4;

    @XmlTransient
    private int             tableRole;

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        if (_abstract)
            b.put(OPT_ABSTRACT);
        switch (cacheStrategy) {
        case CACHE_DICT:
            b.put(OPT_CACHE_DICT);
            break;
        case CACHE_REDUNDANT:
            b.put(OPT_CACHE_REDUNDANT);
            break;
        case CACHE_TRANSIENT:
            b.put(OPT_CACHE_TRANSIENT);
            break;
        }
        if (readOnly)
            b.put(OPT_READ_ONLY);
        switch (tableRole) {
        case MAINTABLE:
            break;
        case SUBTABLE_GENERAL:
            b.put(OPT_SUB_GENERAL);
            break;
        case SUBTABLE_MOREFIELDS:
            b.put(OPT_SUB_MOREFIELDS);
            break;
        case SUBTABLE_PROPERTIES:
            b.put(OPT_SUB_PROPERTIES);
            break;
        case SUBTABLE_DETAILS:
            b.put(OPT_SUB_DETAILS);
            break;
        }
        return b.toString();
    }

    public void setOpts(String opts) {
        _abstract = false;
        tableRole = MAINTABLE;
        cacheStrategy = 0;
        for (Term t : TermParser.parse(__dict__, opts))
            switch (t.getId()) {
            case OPT_ABSTRACT:
                _abstract = true;
                break;
            case OPT_CACHE_DICT:
                cacheStrategy = CACHE_DICT;
                break;
            case OPT_CACHE_REDUNDANT:
                cacheStrategy = CACHE_REDUNDANT;
                break;
            case OPT_CACHE_TRANSIENT:
                cacheStrategy = CACHE_TRANSIENT;
                break;
            case OPT_READ_ONLY:
                readOnly = true;
                break;
            case OPT_SUB_GENERAL:
                tableRole = SUBTABLE_GENERAL;
                break;
            case OPT_SUB_MOREFIELDS:
                tableRole = SUBTABLE_MOREFIELDS;
                break;
            case OPT_SUB_PROPERTIES:
                tableRole = SUBTABLE_PROPERTIES;
                break;
            case OPT_SUB_DETAILS:
                tableRole = SUBTABLE_DETAILS;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
    }

    public boolean isAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public int getTableRole() {
        return tableRole;
    }

    public void setTableRole(int tableRole) {
        this.tableRole = tableRole;
    }

    static final int      OPT_ABSTRACT        = 1;
    static final int      OPT_CACHE_DICT      = 2;
    static final int      OPT_CACHE_REDUNDANT = 3;
    static final int      OPT_CACHE_TRANSIENT = 4;
    static final int      OPT_READ_ONLY       = 5;
    static final int      OPT_SUB_GENERAL     = 6;
    static final int      OPT_SUB_MOREFIELDS  = 7;
    static final int      OPT_SUB_PROPERTIES  = 8;
    static final int      OPT_SUB_DETAILS     = 9;
    static final TermDict __dict__            = new TermDict();
    static {
        __dict__.define(OPT_ABSTRACT, "c");
        __dict__.define(OPT_CACHE_DICT, "D");
        __dict__.define(OPT_CACHE_REDUNDANT, "Dr");
        __dict__.define(OPT_CACHE_TRANSIENT, "T");
        __dict__.define(OPT_READ_ONLY, "Ro");
        __dict__.define(OPT_SUB_GENERAL, "S");
        __dict__.define(OPT_SUB_MOREFIELDS, "Sm");
        __dict__.define(OPT_SUB_PROPERTIES, "Sp");
        __dict__.define(OPT_SUB_DETAILS, "Sd");
    }

    @Override
    public void accept(PDBVisitor visitor) {
        visitor.visit(this);
        assert fields != null;
        for (Field field : this.fields)
            field.accept(visitor);
        if (indexes != null)
            for (Index index : this.indexes)
                index.accept(visitor);
        if (instances != null)
            for (Instance instance : this.instances)
                instance.accept(visitor);
    }

    @XmlTransient
    TextMap<Field> fieldMap;
    @XmlTransient
    TextMap<Index> indexMap;

    public Field getField(String name) {
        return fieldMap.get(name);
    }

    public Index getIndex(String name) {
        return indexMap.get(name);
    }

}
