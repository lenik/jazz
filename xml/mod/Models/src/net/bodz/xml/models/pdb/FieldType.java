package net.bodz.xml.models.pdb;

import java.sql.Types;

import javax.xml.bind.annotation.XmlAttribute;

import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

public class FieldType {

    private int sqlType;
    private int precision;
    private int scale;

    public FieldType() {
        sqlType = Types.VARCHAR;
        precision = 32;
        scale = 0;
    }

    public FieldType(String opts) {
        setOpts(opts);
    }

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        switch (sqlType) {
        case Types.BOOLEAN:
        case Types.BIT:
        case Types.DATE:
        case Types.TIME:
        case Types.TIMESTAMP:
        case Types.FLOAT:
        case Types.DOUBLE:
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:
        case Types.BIGINT:
        case Types.DECIMAL:
        case Types.NUMERIC:
        case Types.REAL:

        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
        case Types.CLOB:

        case Types.NCHAR:
        case Types.NVARCHAR:
        case Types.LONGNVARCHAR:
        case Types.NCLOB:

        case Types.BINARY:
        case Types.VARBINARY:
        case Types.LONGVARBINARY:
        case Types.BLOB:
        }
        return b.toString();
    }

    public void setOpts(String value) {
        sqlType = Types.VARCHAR;
        precision = 32;
        scale = 0;
        for (Term t : TermParser.parse(__dict__, value)) {
            switch (t.getId()) {
            case OPT_BIT:
                sqlType = Types.BOOLEAN;
                sqlType = Types.BIT;
                break;
            case OPT_DATETIME:
                sqlType = Types.DATE; // DATETIME?
                break;
            case OPT_DATE:
                sqlType = Types.DATE;
                break;
            case OPT_TIME:
                sqlType = Types.TIME;
                break;
            case OPT_TIMESTAMP:
                sqlType = Types.TIMESTAMP;
                break;
            case OPT_FLOAT:
                sqlType = Types.FLOAT;
                sqlType = Types.DOUBLE;
                break;
            case OPT_INT:
                sqlType = Types.TINYINT;
                sqlType = Types.SMALLINT;
                sqlType = Types.INTEGER;
                sqlType = Types.BIGINT;
                break;
            case OPT_MONEY:
                sqlType = Types.DECIMAL;
                break;
            case OPT_NUMBER:
                sqlType = Types.NUMERIC;
                sqlType = Types.REAL;
                break;
            case OPT_CHAR:
                sqlType = Types.CHAR;
                sqlType = Types.VARCHAR;
                sqlType = Types.LONGVARCHAR;
                sqlType = Types.CLOB;
                break;
            case OPT_NCHAR:
                sqlType = Types.NCHAR;
                sqlType = Types.NVARCHAR;
                sqlType = Types.LONGNVARCHAR;
                sqlType = Types.NCLOB;
                break;
            case OPT_BINARY:
                sqlType = Types.BINARY;
                sqlType = Types.VARBINARY;
                sqlType = Types.LONGVARBINARY;
                sqlType = Types.BLOB;
                break;
            default:
                throw new IllegalArgumentException("Bad opt: " + t.getName());
            }
        }
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    /**
     * <pre>
     * B[min,max] = binary
     * Bt = bit/boolean
     * C[min,max] = 
     *     char/varchar/text
     * Cn[min,max] = 
     *     nchar/nvarchar/ntext
     * T? = datetime
     *     Td=date
     *     Tt=time
     *     Ts=timestamp
     * F?=float (default sys/float)
     * I?=integer (default sys/int)
     *     I8, I16, I32, I64, etc.
     * M[totlen,declen] = money
     * N[totlen,declen] = number
     * </pre>
     */
    static final int      OPT_BINARY    = 1;
    static final int      OPT_BIT       = 2;
    static final int      OPT_CHAR      = 3;
    static final int      OPT_NCHAR     = 4;
    static final int      OPT_DATETIME  = 5;
    static final int      OPT_DATE      = 6;
    static final int      OPT_TIME      = 7;
    static final int      OPT_TIMESTAMP = 8;
    static final int      OPT_FLOAT     = 9;
    static final int      OPT_INT       = 10;
    static final int      OPT_MONEY     = 11;
    static final int      OPT_NUMBER    = 12;

    static final TermDict __dict__      = new TermDict();
    static {
        __dict__.define(OPT_BINARY, "B");
        __dict__.define(OPT_BIT, "Bt");
        __dict__.define(OPT_CHAR, "C");
        __dict__.define(OPT_NCHAR, "Cn");
        __dict__.define(OPT_DATETIME, "T");
        __dict__.define(OPT_DATE, "Td");
        __dict__.define(OPT_TIME, "Tt");
        __dict__.define(OPT_TIMESTAMP, "Ts");
        __dict__.define(OPT_FLOAT, "F");
        __dict__.define(OPT_INT, "I");
        __dict__.define(OPT_MONEY, "M");
        __dict__.define(OPT_NUMBER, "N");
    }

    @Override
    public String toString() {
        return getOpts();
    }

}
