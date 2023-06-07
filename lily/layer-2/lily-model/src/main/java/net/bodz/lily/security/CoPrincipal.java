package net.bodz.lily.security;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.file.UploadHint;
import net.bodz.lily.model.base.IdEntity;

@UploadHint
public abstract class CoPrincipal
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_LOGIN_NAME = 30;
    public static final int N_FULL_NAME = 40;

    CoPrincipalProperties properties;

    /**
     * @label Login Name
     * @label.zh 登录名
     */
    public String getName() {
        // reuse the member var
        return super.getUniqName();
    }

    public void setName(String name) {
        // reuse the member var
        super.setUniqName(name);
    }

    @Deprecated
    @Override
    public String getUniqName() {
        return super.getUniqName();
    }

    @Deprecated
    @Override
    public void setUniqName(String uniqName) {
        super.setUniqName(uniqName);
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
        if (properties == null) {
            synchronized (this) {
                if (properties == null) {
                    properties = createProperties();
                }
            }
        }
        return properties;
    }

    public void setProperties(CoPrincipalProperties properties) {
        this.properties = properties;
    }

    public CoPrincipalProperties createProperties() {
        return new CoPrincipalProperties();
    }

}
