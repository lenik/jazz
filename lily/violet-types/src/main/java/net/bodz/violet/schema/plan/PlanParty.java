package net.bodz.violet.schema.plan;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.TextInput;

@Table(schema = "violet", name = "plan_party")
public class PlanParty
        extends _PlanParty_stuff {

    private static final long serialVersionUID = 1L;

    @TextInput(maxLength = N_DESCRIPTION)
    @Override
    public String getDescription() {
        return super.getDescription();
    }

}
