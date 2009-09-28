package net.bodz.inplas.kid.stype;

import net.bodz.bas.types.util.Strings;
import net.bodz.inplas.kid.JSUtil;
import net.bodz.inplas.kid.JavaUtil;

public class ST_Number extends STypeBase {

    protected Double max;

    public double min() {
        return -max();
    }

    public double max() {
        if (max == null) {
            max = Math.pow(10, precision) - 1.0;
        }
        return max;
    }

    /**
     * <code>
     * static void checkNAME(Object nAME) throws CheckException {
     *     double v = Value.getDouble(nAME, Double.NaN); 
     *     if (v == Double.NaN)
     *         throw new CheckException("nAME(" + nAME + ") isn't a number"); 
     *     if (nAME &lt; Type.Min)
     *         throw new CheckException("nAME(" + nAME + ") is smaller than minimum value of (Type.Min)");
     *     if (nAME &gt; Type.Max)
     *         throw new CheckException("nAME(" + nAME + ") is greater than maximum value of (Type.Max)");
     * }
     * </code>
     */
    public String check_Java(String name) {
        String funName = JavaUtil.getJavaName("check" + Strings.ucfirst(name));
        String varName = JavaUtil.getJavaName(Strings.lcfirst(name));
        StringBuffer java = new StringBuffer();
        java.append("static void " + funName + "(Object " + varName + ") {\n");
        java.append("    double v = Value.getDouble(" + varName
                + ", Double.NaN); \n");
        java.append("    if (v == Double.NaN) \n");
        java.append("        throw new CheckException(\"" + varName + "(\" + "
                + varName + " + \") isn't a number\"); \n");
        java.append("    if (" + varName + " < " + min() + ") \n");
        java.append("        throw new CheckException(\"" + varName + "(\" + "
                + varName + " + \") is smaller than minimum value of (" + min()
                + ")\"); \n");
        java.append("    if (" + varName + " > " + max() + ") \n");
        java.append("        throw new CheckException(\"" + varName + "(\" + "
                + varName + " + \") is greater than maximum value of (" + max()
                + ")\"); \n");
        java.append("}\n");

        return java.toString();
    }

    /**
     * <code>
     * function checkNAME(nAME) {
     *     if (! isNumeric(nAME))
     *         return false; 
     *     if (nAME &lt; Type.Min)
     *         return false;
     *     if (nAME &gt; Type.Max)
     *         return false;
     *     return true; 
     * }
     * </code>
     */
    public String check_JS(String name) {
        // assert name != null;
        String funName = JSUtil.getJSName("check" + Strings.ucfirst(name));
        String varName = JSUtil.getJSName(Strings.lcfirst(name));
        StringBuffer js = new StringBuffer();
        js.append("function " + funName + "(" + varName + ") {\n");
        js.append("    if (" + varName + " < " + min() + ") return false; \n");
        js.append("    if (" + varName + " > " + max() + ") return false; \n");
        js.append("    return true; \n");
        js.append("}\n");
        return js.toString();
    }

}
