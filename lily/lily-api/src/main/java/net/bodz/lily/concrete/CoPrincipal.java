package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.meta.FieldGroupVue;
import net.bodz.lily.meta.TsTyped;

@FieldGroupVue
@HaveAttachments
@TsTyped
public abstract class CoPrincipal
        extends CoImaged<Integer>
        implements
            IUniqueNamed,
            IHaveProperties {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_NAME = "name";

    public static final int N_NAME = 30;
    public static final int N_LABEL = 40;

    String name;

    /**
     * @label Login Name
     * @label.zh 登录名
     */
    @Column(name = FIELD_NAME, precision = N_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = FIELD_NAME, precision = N_NAME)
    @Derived
    @Override
    public String getUniqueName() {
        return getName();
    }

    @Override
    public void setUniqueName(String name) {
        setName(name);
    }

    /**
     * @label Full Name
     * @label.zh 全称
     */
    @Column(name = FIELD_LABEL, precision = N_LABEL)
    @Derived
    @TextInput(maxLength = N_LABEL)
    public final String getFullName() {
        return getLabel();
    }

    public final void setFullName(String fullName) {
        setLabel(fullName);
    }

}
