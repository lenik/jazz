package net.bodz.xml.models.pdb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link FieldTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Field {

    @XmlAttribute(required = true)
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

    @XmlTransient
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

    @XmlTransient
    private Index           index;

    @XmlTransient
    private String          timestampForFields;
    @XmlTransient
    private String          versionForFields;

    public static final int DEFAULT_VALUE = 1;
    public static final int DEFAULT_EXPR  = 2;
    public static final int IDENTITY      = 3;
    @XmlTransient
    private int             defaultStrategy;
    @XmlTransient
    private String          _default;

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        if (nullable)
            b.put(OPT_NULLABLE);
        if (index != null)
            b.put(index.getOpts());
        if (timestampForFields != null) {
            b.put(OPT_TIMESTAMP);
            b.putParameters(timestampForFields);
        }
        if (versionForFields != null) {
            b.put(OPT_VERSION);
            b.putParameters(versionForFields);
        }
        switch (defaultStrategy) {
        case DEFAULT_EXPR:
            b.put(OPT_DEFAULT_VALUE);
            b.putParameters(_default);
            break;
        case DEFAULT_VALUE:
            b.put(OPT_DEFAULT_EXPR);
            b.putParameters(_default);
            break;
        case IDENTITY:
            b.put(OPT_IDENTITY);
            b.putParameters(_default);
            break;
        default:
            throw new UnexpectedException("bad default strategy: " + defaultStrategy);
        }
        return b.toString();
    }

    public void setOpts(String opts) {
        nullable = false;
        index = null;
        timestampForFields = null;
        versionForFields = null;
        defaultStrategy = 0;
        _default = null;

        for (Term t : TermParser.parse(__dict__, opts)) {
            switch (t.getId()) {
            case OPT_NULLABLE:
                nullable = true;
                break;
            case OPT_PKEY:
            case OPT_FKEY:
            case OPT_UNIQUE:
            case OPT_INDEX:
                index = new Index(); // pass owner Table?
                index.setOpts(t.toString());
                break;
            case OPT_TIMESTAMP:
                timestampForFields = t.getRawParameter();
                break;
            case OPT_VERSION:
                versionForFields = t.getRawParameter();
                break;
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
                break;
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

    public Index getIndex() {
        return index;
    }

    public void setIndex(Index index) {
        this.index = index;
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

    static final int      OPT_NULLABLE      = 1;
    static final int      OPT_PKEY          = 2;
    static final int      OPT_FKEY          = 3;
    static final int      OPT_UNIQUE        = 4;
    static final int      OPT_INDEX         = 5;
    static final int      OPT_TIMESTAMP     = 6;
    static final int      OPT_VERSION       = 7;
    static final int      OPT_DEFAULT_VALUE = 8;
    static final int      OPT_DEFAULT_EXPR  = 9;
    static final int      OPT_IDENTITY      = 10;
    static final TermDict __dict__          = new TermDict();
    static {
        __dict__.define(OPT_NULLABLE, "N");
        __dict__.define(OPT_PKEY, "K"); // see Index.OPT_PKEY
        __dict__.define(OPT_FKEY, "F"); // see Index.OPT_FKEY
        __dict__.define(OPT_UNIQUE, "U"); // see Index.OPT_UNIQUE
        __dict__.define(OPT_INDEX, "I"); // see Index.OPT_INDEX
        __dict__.define(OPT_TIMESTAMP, "T");
        __dict__.define(OPT_VERSION, "V");
        __dict__.define(OPT_DEFAULT_VALUE, "D");
        __dict__.define(OPT_DEFAULT_EXPR, "Dx");
        __dict__.define(OPT_IDENTITY, "Di");
    }

}
