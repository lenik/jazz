package net.bodz.bas.repr.xfiles;

import java.io.Serializable;

public class XFile
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String op;
    private final String identity;

    public XFile(String name, String opIdentity) {
        if (name == null)
            throw new NullPointerException("name");
        if (opIdentity == null)
            throw new NullPointerException("opIdentity");

        this.name = name;

        int slash = opIdentity.indexOf('/');
        if (slash == -1) {
            op = null;
            identity = opIdentity;
        } else {
            op = opIdentity.substring(0, slash);
            identity = opIdentity.substring(slash + 1);
        }
    }

    /**
     * Validate identity against op-name, current period, etc.
     */
    public boolean validate() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identity == null) ? 0 : identity.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((op == null) ? 0 : op.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        XFile other = (XFile) obj;
        if (identity == null) {
            if (other.identity != null)
                return false;
        } else if (!identity.equals(other.identity))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (op == null) {
            if (other.op != null)
                return false;
        } else if (!op.equals(other.op))
            return false;
        return true;
    }

}
