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
import javax.xml.bind.annotation.XmlValue;

import net.bodz.bas.types.TextMap;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link ViewTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "from", "field", "filter", "groupBy", "sort" })
@XmlRootElement(name = "view")
public class View implements PDBElement {

    @XmlAttribute(required = true)
    protected String           name;
    @XmlAttribute
    protected String           label;
    @XmlAttribute
    protected String           tags;
    @XmlAttribute
    protected String           doc;
    @XmlAttribute
    protected Boolean          distinct;

    @XmlElement(required = true)
    protected List<FromType>   from;
    @XmlElement(required = true)
    protected List<View.Field> field;
    protected List<String>     filter;
    @XmlElement(required = true)
    protected View.GroupBy     groupBy;
    @XmlElement(required = true)
    protected View.Sort        sort;

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

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public List<FromType> getFroms() {
        if (from == null) {
            from = new ArrayList<FromType>();
        }
        return this.from;
    }

    public List<View.Field> getFields() {
        if (field == null) {
            field = new ArrayList<View.Field>();
        }
        return this.field;
    }

    public List<String> getFilters() {
        if (filter == null) {
            filter = new ArrayList<String>();
        }
        return this.filter;
    }

    public View.GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(View.GroupBy value) {
        this.groupBy = value;
    }

    public View.Sort getSort() {
        return sort;
    }

    public void setSort(View.Sort value) {
        this.sort = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class Field implements PDBElement {

        @XmlAttribute
        protected String name;

        @XmlAttribute
        protected String label;

        @XmlValue
        protected String value;

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public void accept(PDBVisitor visitor) {
            visitor.visit(this);
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class GroupBy {

        @XmlAttribute(required = true)
        protected String fields;
        @XmlAttribute
        protected String having;

        public String getFields() {
            return fields;
        }

        public void setFields(String fields) {
            this.fields = fields;
        }

        public String getHaving() {
            return having;
        }

        public void setHaving(String having) {
            this.having = having;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "value" })
    public static class Sort {

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String order;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

    }

    public static final int DICT_NONE   = 0;
    public static final int DICT_CACHED = 1;
    public static final int DICT_INFO   = 2;

    @XmlTransient
    private int             dictMode;
    @XmlTransient
    private boolean         readOnly;
    @XmlTransient
    private boolean         _transient;

    public int getDictMode() {
        return dictMode;
    }

    public void setDictMode(int dictMode) {
        this.dictMode = dictMode;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isTransient() {
        return _transient;
    }

    public void setTransient(boolean _transient) {
        this._transient = _transient;
    }

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        switch (dictMode) {
        case DICT_CACHED:
            b.put(OPT_DICT_CACHED);
            break;
        case DICT_INFO:
            b.put(OPT_DICT_INFO);
            break;
        }
        if (readOnly)
            b.put(OPT_READ_ONLY);
        if (_transient)
            b.put(OPT_TRANSIENT);
        return b.toString();
    }

    public void setOpts(String value) {
        dictMode = DICT_NONE;
        readOnly = false;
        _transient = false;
        for (Term t : TermParser.parse(__dict__, value)) {
            switch (t.getId()) {
            case OPT_DICT_CACHED:
                dictMode = DICT_CACHED;
                break;
            case OPT_DICT_INFO:
                dictMode = DICT_INFO;
                break;
            case OPT_READ_ONLY:
                readOnly = true;
                break;
            case OPT_TRANSIENT:
                _transient = true;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
        }
    }

    static final int      OPT_DICT_CACHED = 1;
    static final int      OPT_DICT_INFO   = 2;             // Redundant??
    static final int      OPT_READ_ONLY   = 3;
    static final int      OPT_TRANSIENT   = 4;
    static final TermDict __dict__        = new TermDict();
    static {
        __dict__.define(OPT_DICT_CACHED, "D");
        __dict__.define(OPT_DICT_INFO, "Dr");
        __dict__.define(OPT_READ_ONLY, "Ro");
        __dict__.define(OPT_TRANSIENT, "T");
    }

    @Override
    public void accept(PDBVisitor visitor) {
        visitor.visit(this);
    }

    @XmlTransient
    TextMap<View.Field> fieldMap;

    public View.Field getField(String name) {
        return fieldMap.get(name);
    }

}
