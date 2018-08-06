package net.bodz.violet.issue;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoParameter;

@IdType(Integer.class)
public class IssueParameter
        extends CoParameter<IssueParameter> {

    private static final long serialVersionUID = 1L;

    public IssueParameter() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
