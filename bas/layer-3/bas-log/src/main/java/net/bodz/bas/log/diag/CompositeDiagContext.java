package net.bodz.bas.log.diag;

import java.util.ArrayList;
import java.util.ServiceLoader;

public class CompositeDiagContext
        extends ArrayList<IDiagContextTeller> {

    private static final long serialVersionUID = 1L;

    String delimiter = " ";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for (IDiagContextTeller item : this) {
            String str = item.getDiagContextName();
            if (str == null || str.isEmpty())
                continue;
            if (n++ != 0)
                sb.append(delimiter);
            sb.append(str);
        }
        return sb.toString();
    }

    public static final CompositeDiagContext CONTEXTS = new CompositeDiagContext();
    static {
        for (IContextsCdcConfigurer c : ServiceLoader.load(IContextsCdcConfigurer.class))
            c.configure(CONTEXTS);
    }

}
