package net.bodz.xml.models.pdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Field {

    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected String label;
    @XmlAttribute
    protected String tags;
    @XmlAttribute
    protected String doc;

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

    private FieldType type;

    @XmlAttribute
    public String getType() {
        if (type == null)
            type = new FieldType();
        return type.getOpts();
    }

    public void setType(String type) {
        this.type = new FieldType(type);
    }

    @XmlTransient
    private boolean         nullable;

    public static final int INDEX_NONE        = 1;
    public static final int INDEX_NORMAL      = 1;
    public static final int INDEX_PRIMARY_KEY = 2;
    public static final int INDEX_FOREIGN_KEY = 3;
    @XmlTransient
    private int             indexType;
    @XmlTransient
    private boolean         clustered;
    @XmlTransient
    private boolean         unique;

    @XmlTransient
    private String          timestampForFields;
    @XmlTransient
    private String          versionForFields;

    public static final int DEFAULT_VALUE     = 1;
    public static final int DEFAULT_EXPR      = 2;
    public static final int IDENTITY          = 3;
    @XmlTransient
    private int             defaultStrategy;
    @XmlTransient
    private String          _default;

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        return b.toString();
    }

    public void setOpts(String opts) {
        nullable = false;
        indexType = INDEX_NONE;
        clustered = false;
        unique = false;
        timestampForFields = null;
        versionForFields = null;
        defaultStrategy = 0;
        _default = null;
        for (Term t : TermParser.parse(__dict__, opts)) {
            switch (t.getId()) {
            case OPT_NULLABLE:
                nullable = true;
            case OPT_PRIMARY_KEY:
                break;
            case OPT_FOREIGN_KEY:
            case OPT_INDEX:
                indexType = INDEX_NORMAL;
            case OPT_CLUSTERED_INDEX:
                clustered = true;
            case OPT_UNIQUE:
                unique = true;
            case OPT_TIMESTAMP:
            case OPT_VERSION:
            case OPT_DEFAULT_VALUE:
                defaultStrategy = DEFAULT_VALUE;
                _default = t.getRawParameter();
                break;
            case OPT_DEFAULT_EXPR:
                defaultStrategy = DEFAULT_EXPR;
                _default = t.getRawParameter();
                break;
            case OPT_IDENTITY:
                defaultStrategy = IDENTITY;
                _default = t.getRawParameter();
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
        }
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getIndexType() {
        return indexType;
    }

    public void setIndexType(int indexType) {
        this.indexType = indexType;
    }

    public boolean isClustered() {
        return clustered;
    }

    public void setClustered(boolean clustered) {
        this.clustered = clustered;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getTimestampForFields() {
        return timestampForFields;
    }

    public void setTimestampForFields(String timestampForFields) {
        this.timestampForFields = timestampForFields;
    }

    public String getVersionForFields() {
        return versionForFields;
    }

    public void setVersionForFields(String versionForFields) {
        this.versionForFields = versionForFields;
    }

    public int getDefaultStrategy() {
        return defaultStrategy;
    }

    public void setDefaultStrategy(int defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
    }

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    static final int      OPT_NULLABLE        = 1;
    static final int      OPT_PRIMARY_KEY     = 2;
    static final int      OPT_FOREIGN_KEY     = 3;
    static final int      OPT_INDEX           = 4;
    static final int      OPT_CLUSTERED_INDEX = 5;
    static final int      OPT_UNIQUE          = 6;
    static final int      OPT_TIMESTAMP       = 7;
    static final int      OPT_VERSION         = 8;
    static final int      OPT_DEFAULT_VALUE   = 9;
    static final int      OPT_DEFAULT_EXPR    = 10;
    static final int      OPT_IDENTITY        = 11;
    static final TermDict __dict__            = new TermDict();
    static {
        __dict__.define(OPT_NULLABLE, "N");
        __dict__.define(OPT_PRIMARY_KEY, "K");
        __dict__.define(OPT_FOREIGN_KEY, "F");
        __dict__.define(OPT_INDEX, "I");
        __dict__.define(OPT_CLUSTERED_INDEX, "Ic");
        __dict__.define(OPT_UNIQUE, "U");
        __dict__.define(OPT_TIMESTAMP, "T");
        __dict__.define(OPT_VERSION, "V");
        __dict__.define(OPT_DEFAULT_VALUE, "D");
        __dict__.define(OPT_DEFAULT_EXPR, "Dx");
        __dict__.define(OPT_IDENTITY, "Di");
    }

}
