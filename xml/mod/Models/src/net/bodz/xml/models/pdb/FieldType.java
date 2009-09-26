package net.bodz.xml.models.pdb;

import java.util.Iterator;

import javax.xml.bind.annotation.XmlAttribute;

import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.xml.util.Term;
import net.bodz.xml.util.TermBuilder;
import net.bodz.xml.util.TermDict;
import net.bodz.xml.util.TermParser;

/**
 * @test {@link FieldTypeTest}
 */
public class FieldType {

    public static final int BOOLEAN = 1;
    public static final int BIT     = 2;
    public static final int INTEGER = 3;  // precision: number of bytes
    public static final int FLOAT   = 4;  // precision: number of bytes
    public static final int DECIMAL = 5;
    public static final int REAL    = 6;
    public static final int TIME    = 7;  // precision: 0-timestamp, 1-time, 2-date, 3-datetime
    public static final int STRING  = 10;
    public static final int UNICODE = 12;
    public static final int BINARY  = 14;
    public static final int XML     = 16;
    public static final int OBJECT  = 100;

    private int             sqlType;
    private int             precision;
    private int             scale;
    private boolean         varLength;

    public FieldType() {
        sqlType = UNICODE;
        varLength = true;
        precision = 32;
        scale = 0;
    }

    public FieldType(String opts) {
        setOpts(opts);
    }

    @XmlAttribute
    public String getOpts() {
        TermBuilder b = new TermBuilder(__dict__);
        boolean putLength = false;
        switch (sqlType) {
        case BOOLEAN:
            b.put(OPT_BOOLEAN);
            if (precision != 1)
                b.putBounds(precision);
            break;
        case BIT:
            b.put(OPT_BIT);
            if (precision != 1)
                b.putBounds(precision);
            break;
        case INTEGER:
            b.put(OPT_INT);
            if (precision != SIZE_INT)
                b.putIndex(precision);
            break;
        case FLOAT:
            b.put(OPT_FLOAT);
            if (precision != SIZE_FLOAT)
                b.putIndex(precision);
            break;
        case DECIMAL:
            if (scale == 0) {
                b.put(OPT_INTEGER);
                if (precision != PREC_INTEGER)
                    b.putIndex(precision);
            } else {
                b.put(OPT_NUMERIC);
                b.putBounds(precision, scale);
            }
            break;
        case REAL:
            b.put(OPT_REAL);
            if (precision != 12)
                b.putIndex(precision);
            break;
        case TIME:
            switch (scale) {
            case 0:
                b.put(OPT_TIMESTAMP);
                break;
            case 1:
                b.put(OPT_TIME);
                break;
            case 2:
                b.put(OPT_DATE);
                break;
            case 3:
                b.put(OPT_DATETIME);
                break;
            default:
                throw new UnexpectedException("datetime.scale=" + scale);
            }
            break;
        case STRING:
            b.put(OPT_STRING);
            putLength = true;
            break;
        case UNICODE:
            b.put(OPT_UNICODE);
            putLength = true;
            break;
        case BINARY:
            b.put(OPT_BINARY); // binary not follow the PREC_STRING.
            if (varLength)
                b.putBounds(0, precision);
            else
                b.putBounds(precision);
            break;
        case XML:
            b.put(OPT_XML);
            break;
        case OBJECT:
            b.put(OPT_OBJECT);
            break;
        }
        if (putLength) {
            if (varLength)
                if (precision == PREC_STRING)
                    b.putBounds(0, 0);
                else
                    b.putBounds(0, precision);
            else if (precision != PREC_STRING)
                b.putBounds(precision);
        }
        return b.toString();
    }

    public void setOpts(String value) {
        precision = 0;
        scale = 0;
        Iterator<Term> terms = TermParser.parse(__dict__, value).iterator();
        Term t = terms.next();
        int size;
        boolean checkLen = false;
        switch (t.getId()) {
        case OPT_BOOLEAN:
            sqlType = BOOLEAN;
            precision = t.getIndex(1);
            break;
        case OPT_BIT:
            sqlType = BIT;
            precision = t.getIndex(1);
            break;
        case OPT_INT:
            sqlType = INTEGER;
            size = t.getIndex(SIZE_INT);
            switch (size) {
            case 1:
            case 2:
            case 4:
            case 8:
                precision = size;
                break;
            default:
                throw new IllegalArgumentException("bad int size: " + t);
            }
            break;
        case OPT_FLOAT:
            sqlType = FLOAT;
            size = t.getIndex(SIZE_FLOAT);
            switch (size) {
            case 4: // float
            case 8: // double
                precision = size;
                break;
            default:
                throw new IllegalArgumentException("bad float size: " + t);
            }
            break;
        case OPT_NUMERIC:
            sqlType = DECIMAL;
            if (t.getDimension() == 0 || t.getBound(0) == 0)
                throw new IllegalArgumentException("precision isn't specified: " + t);
            precision = t.getBound(0);
            scale = t.getBound(1, 0);
            if (scale > precision)
                throw new IllegalArgumentException("scale>precision: " + t);
            break;
        case OPT_INTEGER:
            sqlType = DECIMAL;
            precision = t.getIndex(PREC_INTEGER);
            break;
        case OPT_REAL:
            sqlType = REAL;
            precision = t.getIndex(PREC_REAL);
            break;
        case OPT_DATETIME:
            precision++;
        case OPT_DATE:
            precision++;
        case OPT_TIME:
            precision++;
        case OPT_TIMESTAMP:
            sqlType = TIME;
            break;
        case OPT_STRING:
            sqlType = STRING;
            checkLen = true;
            break;
        case OPT_UNICODE:
            sqlType = UNICODE;
            checkLen = true;
            break;
        case OPT_BINARY:
            sqlType = BINARY;
            checkLen = true;
            break;
        case OPT_XML:
            sqlType = XML;
            break;
        case OPT_OBJECT:
            sqlType = OBJECT;
            break;
        default:
            throw new IllegalArgumentException("Bad opt: " + t);
        }
        if (checkLen) {
            if (t.getDimension() >= 2) {
                int min = t.getBound(0);
                int max = t.getBound(1, PREC_STRING);
                if (min > max)
                    throw new IllegalArgumentException("Bad bounds: " + t);
                varLength = min != max;
                precision = max;
            } else {
                varLength = false;
                precision = t.getBound(0, PREC_STRING);
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

    static final int      OPT_BOOLEAN   = 1;
    static final int      OPT_BIT       = 2;
    static final int      OPT_INT       = 3;
    static final int      OPT_FLOAT     = 4;
    static final int      OPT_NUMERIC   = 5;
    static final int      OPT_INTEGER   = 6;
    static final int      OPT_REAL      = 7;
    static final int      OPT_DATETIME  = 8;
    static final int      OPT_DATE      = 9;
    static final int      OPT_TIME      = 10;
    static final int      OPT_TIMESTAMP = 11;
    static final int      OPT_STRING    = 12;
    static final int      OPT_UNICODE   = 13;
    static final int      OPT_BINARY    = 14;
    static final int      OPT_XML       = 15;
    static final int      OPT_OBJECT    = 16;

    static final int      SIZE_INT      = 4;
    static final int      SIZE_FLOAT    = 4;
    static final int      PREC_INTEGER  = 9;
    static final int      PREC_REAL     = 12;
    static final int      PREC_STRING   = 32;

    static final TermDict __dict__      = new TermDict();
    static {
        __dict__.define(OPT_BOOLEAN, "b");
        __dict__.define(OPT_BIT, "B");
        __dict__.define(OPT_INT, "I");
        __dict__.define(OPT_FLOAT, "F");
        __dict__.define(OPT_NUMERIC, "N");
        __dict__.define(OPT_INTEGER, "Z");
        __dict__.define(OPT_REAL, "R");
        __dict__.define(OPT_DATETIME, "T");
        __dict__.define(OPT_DATE, "Td");
        __dict__.define(OPT_TIME, "Tt");
        __dict__.define(OPT_TIMESTAMP, "Ts");
        __dict__.define(OPT_STRING, "C");
        __dict__.define(OPT_UNICODE, "U");
        __dict__.define(OPT_BINARY, "Y");
        __dict__.define(OPT_XML, "X");
        __dict__.define(OPT_OBJECT, "O");
    }

    @Override
    public String toString() {
        return getOpts();
    }

}
