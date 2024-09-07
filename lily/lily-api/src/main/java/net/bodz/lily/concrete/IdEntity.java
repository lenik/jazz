package net.bodz.lily.concrete;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns(IdEntity.FIELD_ID)
@PrimaryKeyProperties("id")
@TsTyped
@TypeParameters({ TypeParamType.ID_TYPE })
public abstract class IdEntity<Id>
        extends CoEntity<Id> {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_ID = "id";

    @Column(name = FIELD_ID, nullable = false)
    @GeneratedValue // possible generated?
    public Id getId() {
        return id();
    }

    public void setId(Id id) {
        id(id);
    }

}
