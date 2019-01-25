package net.bodz.lily.security;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.lily.model.base.CoEntity;

@UploadHint
public abstract class CoPrincipal
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_LOGIN_NAME = 30;
    public static final int N_FULL_NAME = 40;

    String name;
    CoPrincipalProperties properties;

    /**
     * @label Login Name
     * @label.zh 登录名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @label Full Name
     * @label.zh 全称
     */
    @TextInput(maxLength = N_FULL_NAME)
    public final String getFullName() {
        return getLabel();
    }

    public final void setFullName(String fullName) {
        setLabel(fullName);
    }

    @Override
    public CoPrincipalProperties getProperties() {
        return properties;
    }

}
