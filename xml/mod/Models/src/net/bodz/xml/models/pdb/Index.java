package net.bodz.xml.models.pdb;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link IndexTest}
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "fields" })
public class Index implements PDBElement {

    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected String label;
    @XmlAttribute
    protected String tags;
    @XmlAttribute
    protected String doc;
    @XmlAttribute
    protected String ref;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Field {

        @XmlAttribute
        protected String local;
        @XmlAttribute
        protected String remote;

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getRemote() {
            return remote;
        }

        public void setRemote(String remote) {
            this.remote = remote;
        }

    }

    @XmlTransient
    protected List<Index.Field> fields;

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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @XmlElement
    public List<Index.Field> getFields() {
        if (fields == null)
            fields = new ArrayList<Index.Field>();
        return this.fields;
    }

    public void setFields(List<Index.Field> fields) {
        this.fields = fields;
    }

    @XmlTransient
    private boolean         primary;
    @XmlTransient
    private boolean         clustered;
    @XmlTransient
    private boolean         unique;

    /** 1:1 relation */
    public static final int ONE_TO_ONE   = 0;

    /** 1:n relation */
    public static final int ONE_TO_MANY  = 1;

    /** n:1 relation */
    public static final int MANY_TO_ONE  = 2;

    /** n:m relation */
    public static final int MANY_TO_MANY = 3;

    @XmlTransient
    private int             quantifier;

    /** free reference, for describing purpose */
    public static final int LAX          = 0;

    /** force keeping integrity, forbidden referent's delete/update */
    public static final int STRICT       = 1;

    @XmlTransient
    private int             refIntegrity;

    /** no operation when referent is deleted/updated */
    public static final int NONE         = 0;

    /** forbid to delete/update the referent */
    public static final int FORBID       = 1;

    /** delete/update selves when referent is deleted/updated */
    public static final int CASCADE      = 2;

    /** set selves to null when referent is deleted/updated */
    public static final int SET_NULL     = 3;

    @XmlTransient
    private int             deleteStrategy;
    @XmlTransient
    private int             updateStrategy;

    /**
     * <pre>
     * opts: 
     *     [KFIU]〈id〉(params,  n:m X remote)
     *     K=primary key
     *     F=foreign key
     *     I=index
     *     U=unique
     *     
     *     X: => normal ref-integrity
     *         --> no ref-integrity
     *         -> set-null
     *         >> cascade
     * </pre>
     */
    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        int token;
        if (primary)
            token = OPT_PKEY;
        else if (ref != null)
            token = OPT_FKEY;
        else if (unique)
            token = OPT_UNIQUE;
        else
            token = OPT_INDEX;
        b.put(token);
        if (name != null) {
            String tp = name;
            if (label != null)
                tp += "=" + label;
            b.putTypeParameter(tp);
        }
        List<String> parameters = new ArrayList<String>();
        if (clustered)
            parameters.add("clustered");
        if (unique)
            if (token != OPT_UNIQUE)
                parameters.add("unique");
        if (ref != null) {
            String s = formatRef();
            parameters.add(s);
        }
        b.putParameters(parameters);
        return b.toString();
    }

    public void setOpts(String value) {
        primary = false;
        unique = false;
        clustered = false;
        ref = null;
        boolean refError = false;
        for (Term t : TermParser.parse(__dict__, value)) {
            switch (t.getId()) {
            case OPT_PKEY:
                primary = true;
                // unique = true;
                break;
            case OPT_FKEY:
                refError = true;
                break;
            case OPT_INDEX:
                break;
            case OPT_UNIQUE:
                unique = true;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
            String indexName = t.getTypeParameter();
            if (indexName != null) {
                int eq = indexName.indexOf('=');
                if (eq != -1) {
                    label = indexName.substring(eq + 1);
                    indexName = indexName.substring(0, eq);
                }
                // ONLY set the index-name when it is specified,
                // so won't clear the index/@name attribute.
                this.name = indexName;
            }
            String[] parameters = t.getParameters();
            if (parameters != null)
                for (String param : parameters) {
                    Matcher m = containsRelationGraphPattern.matcher(param);
                    if (m.find()) {
                        // if (t.getId() != OPT_FKEY)
                        // throw new IllegalArgumentException("not foreign reference: " + t);
                        try {
                            String quant = m.group(1);
                            String graph = m.group(2);
                            String remote = m.group(3);
                            parseRef(quant, graph, remote, param);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException(e.getMessage(), e);
                        }
                        refError = false;
                    } else if ("clustered".equals(param))
                        clustered = true;
                    else if ("unique".equals(param))
                        unique = true;
                    else if (param.startsWith("delete-")) {
                        param = param.substring(7);
                        if ("cascade".equals(param))
                            deleteStrategy = CASCADE;
                        else if ("set-null".equals(param))
                            deleteStrategy = SET_NULL;
                        else
                            throw new IllegalArgumentException("bad delete strategy: " + param);
                    } else if (param.startsWith("update-")) {
                        if ("cascade".equals(param))
                            updateStrategy = CASCADE;
                        else if ("set-null".equals(param))
                            updateStrategy = SET_NULL;
                        else
                            throw new IllegalArgumentException("bad update strategy: " + param);
                    }
                }
            if (refError)
                throw new IllegalArgumentException("reference isn't defined: " + t);
        }
    }

    static final Pattern containsRelationGraphPattern;
    static {
        containsRelationGraphPattern = Pattern
                .compile("^\\s*(\\w:\\w)\\s*(-->|->|=>|>>|[⇒↗→»])\\s*(.*?)\\s*$");
    }

    void parseRef(String quant, String graph, String remote, String orig) throws ParseException {
        if ("=>".equals(graph) || "⇒".equals(graph)) {
            refIntegrity = STRICT;
            deleteStrategy = updateStrategy = FORBID;
        } else if ("-->".equals(graph) || "↗".equals(graph)) {
            refIntegrity = LAX;
            deleteStrategy = updateStrategy = NONE;
        } else if ("->".equals(graph) || "→".equals(graph)) {
            boolean nullable = false; // owningTable.isNullable();
            refIntegrity = nullable ? STRICT : LAX; // generally is LAX.
            deleteStrategy = SET_NULL;
            updateStrategy = CASCADE;
        } else if (">>".equals(graph) || "»".equals(graph)) {
            refIntegrity = STRICT;
            deleteStrategy = CASCADE;
            updateStrategy = CASCADE;
        }

        quantifier = MANY_TO_ONE;
        if (!quant.isEmpty()) {
            int colon = quant.indexOf(':');
            if (colon == -1 || colon == 0 || colon == quant.length() - 1)
                throw new ParseException("quantifier should be in format \"x:y\": " + quant);
            boolean local1 = "1".equals(quant.substring(0, colon));
            boolean remote1 = "1".equals(quant.substring(colon + 1));
            if (local1)
                if (remote1)
                    quantifier = ONE_TO_ONE;
                else
                    quantifier = ONE_TO_MANY;
            else if (remote1)
                quantifier = MANY_TO_ONE;
            else
                quantifier = MANY_TO_MANY;
        }

        if (remote.isEmpty())
            throw new ParseException("referenced table(fields) isn't specified: " + orig);
        int dot = remote.indexOf('.');
        String remoteField = null;
        if (dot != -1) {
            remoteField = remote.substring(dot + 1).trim();
            remote = remote.substring(0, dot).trim();
        }
        ref = remote;
        if (remoteField == null)
            fields = null;
        else {
            fields = new ArrayList<Field>();
            Field field = new Field();
            field.setRemote(remoteField);
            fields.add(field);
        }
    }

    String formatRef() {
        StringBuffer buf = new StringBuffer();
        switch (quantifier) {
        case ONE_TO_ONE:
            buf.append("1:1");
            break;
        case ONE_TO_MANY:
            buf.append("1:n");
            break;
        case MANY_TO_ONE:
            buf.append("n:1");
            break;
        case MANY_TO_MANY:
            buf.append("n:m");
            break;
        }
        String op = null;
        if (refIntegrity == STRICT) {
            if (deleteStrategy == FORBID && updateStrategy == FORBID)
                op = "⇒";
            else if (deleteStrategy == CASCADE && updateStrategy == CASCADE)
                op = "»";
            else if (deleteStrategy == SET_NULL && updateStrategy == CASCADE)
                op = "→";
        } else if (refIntegrity == LAX) {
            if (deleteStrategy == NONE && updateStrategy == NONE)
                op = "↗";
            else if (deleteStrategy == SET_NULL && updateStrategy == CASCADE)
                op = "→";
        }
        if (op == null) {
            if (refIntegrity == STRICT)
                op = "⇒";
            else
                op = "↗";
            switch (deleteStrategy) {
            case NONE:
                throw new UnexpectedException("strict ref but ignore delete");
            case FORBID:
                break;
            case CASCADE:
                // add parameter: delete-cascade
                break;
            case SET_NULL:
                // add parameter: delete-setnull
                break;
            }
            switch (updateStrategy) {
            case NONE:
                throw new UnexpectedException("strict ref but ignore update");
            case FORBID:
                break;
            case CASCADE:
                // add parameter: update-cascade
                break;
            case SET_NULL:
                // add parameter: update-setnull
                break;
            }
        }

        buf.append(' ');
        buf.append(op);
        buf.append(' ');

        assert ref != null;
        buf.append(ref);
        if (fields != null && fields.size() == 1) {
            Field field = fields.get(0);
            String remoteField = field.getRemote();
            buf.append('.');
            buf.append(remoteField);
        }
        return buf.toString();
    }

    static final int      OPT_PKEY   = 1;
    static final int      OPT_FKEY   = 2;
    static final int      OPT_INDEX  = 3;
    static final int      OPT_UNIQUE = 4;
    static final TermDict __dict__   = new TermDict();
    static {
        __dict__.define(OPT_PKEY, "K");
        __dict__.define(OPT_FKEY, "F");
        __dict__.define(OPT_INDEX, "I");
        __dict__.define(OPT_UNIQUE, "U");
    }

    @Override
    public void accept(PDBVisitor visitor) {
        visitor.visit(this);
    }

    @XmlTransient
    Table table;

    public Table getTable() {
        return table;
    }

}
