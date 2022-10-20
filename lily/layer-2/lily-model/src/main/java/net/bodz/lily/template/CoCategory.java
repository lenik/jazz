package net.bodz.lily.template;

import net.bodz.bas.site.file.UploadHint;
import net.bodz.lily.meta.CriteriaClass;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.model.base.CoNode;

@CriteriaClass(CoCategoryMask.class)
@TypeParameters({ TypeParamType.THIS_TYPE, TypeParamType.ID_TYPE })
@UploadHint
public abstract class CoCategory<self_t extends CoCategory<self_t, Id>, Id>
        extends CoNode<self_t, Id> {

    private static final long serialVersionUID = 1L;

    RichProperties properties = createProperties();

    public CoCategory() {
        super();
    }

    public CoCategory(self_t parent) {
        super(parent);
    }

    public RichProperties createProperties() {
        return new RichProperties();
    }

    @Override
    public RichProperties getProperties() {
        return properties;
    }

}
