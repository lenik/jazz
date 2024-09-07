package net.bodz.lily.concrete;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;

@TsTyped
@TypeParameters({ TypeParamType.THIS_REC, TypeParamType.ID_TYPE })
public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

}
