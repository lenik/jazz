package net.bodz.inplas.kid.stype;

import net.bodz.bas.types.util.Strings;
import net.bodz.inplas.kid.JSUtil;
import net.bodz.inplas.kid.JavaUtil;

public class ST_String extends STypeBase {

    /**
     * <code>
     * static void checkNAME(nAME) throws CheckException {
     *     // if (precision > 0) {  
     *     if (nAME.length > Type.precision)
     *         throw new CheckException("nAME is too long (maxlen=Type.precision)");
     *     // }
     * }
     * </code>
     */
    public String check_Java(String name) {
        // assert name != null;
        String funName = JavaUtil.getJavaName("check" + Strings.ucfirst(name));
        String varName = JavaUtil.getJavaName(Strings.lcfirst(name));
        StringBuffer java = new StringBuffer();
        java.append("static void " + funName + "(String " + varName + ") {\n");
        if (precision > 0) {
            java
                    .append("    if (" + varName + ".length > " + precision
                            + ")\n");
            java.append("        throw new CheckException(\"" + name
                    + " is too long (maxlen=" + precision + ")\");\n");
        }
        java.append("    return true; \n");
        java.append("}\n");
        return java.toString();
    }

    /**
     * <code>
     * function checkNAME(nAME) {
     *     // if (precision > 0) {  
     *     if (nAME.length > Type.precision)
     *         return false;
     *     // }
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
        if (precision > 0)
            js.append("    if (" + varName + ".length > " + precision
                    + ") return false; \n");
        js.append("    return true; \n");
        js.append("}\n");
        return js.toString();
    }

}
