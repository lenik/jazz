package net.bodz.xml.xmod.modcfg;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Comparators;
import net.bodz.bas.types.util.Objects;

import org.jibx.runtime.IUnmarshallingContext;

public class Condition {

    public static final String EQ         = "eq";
    public static final String NE         = "ne";
    public static final String LT         = "lt";
    public static final String LE         = "le";
    public static final String GT         = "gt";
    public static final String GE         = "ge";
    public static final String BOOL_EQ    = "bool-eq";
    public static final String BOOL_NE    = "bool-ne";

    protected Modcfg           modcfg;

    protected String           name;
    protected String           test       = EQ;
    protected String           value      = "true";
    protected boolean          ignoreCase = false;

    public Condition() {
    }

    protected void pre_set(IUnmarshallingContext uctx) throws ParseException {
        Object outer = uctx.getStackObject(2);
        if (!(outer instanceof Modcfg))
            throw new ParseException("element condition must in modcfg/set");
        modcfg = (Modcfg) outer;
    }

    public String getParam() {
        return name;
    }

    public void setParam(String param) {
        this.name = param;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        assert test != null;
        this.test = test.intern();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    private static Set<String> boolTrues;
    static {
        boolTrues = new HashSet<String>();
        boolTrues.add("true");
        boolTrues.add("yes");
        boolTrues.add("1");
    }

    static Comparator<Object>  cmp;
    static {
        cmp = Comparators.NATURAL;
    }

    public boolean test() {
        Param param = modcfg.getParam(name);
        String pval = param.getValue();
        if (test == EQ || test == null)
            return Objects.equals(pval, value);
        else if (test == NE)
            return !Objects.equals(pval, value);
        else if (test == LT)
            return cmp.compare(pval, value) < 0;
        else if (test == LE)
            return cmp.compare(pval, value) <= 0;
        else if (test == GT)
            return cmp.compare(pval, value) > 0;
        else if (test == GE)
            return cmp.compare(pval, value) >= 0;
        else if (test == BOOL_EQ || test == BOOL_NE) {
            boolean bvalue = boolTrues.contains(value.toLowerCase());
            boolean bpval = boolTrues.contains(pval.toLowerCase());
            if (test == BOOL_EQ)
                return bpval == bvalue;
            else
                return bpval != bvalue;
        }
        throw new UnexpectedException("Unknown test operation: " + test);
    }
}
