package net.bodz.lily.pub;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoTag;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ArticleTagType_stuff<this_t extends _ArticleTagType_stuff<this_t>>
        extends CoTag<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "articletag";

    public void initNotNulls() {
    }

}
