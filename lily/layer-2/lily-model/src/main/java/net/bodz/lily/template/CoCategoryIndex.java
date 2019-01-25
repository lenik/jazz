package net.bodz.lily.template;

import java.util.List;

import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.file.UploadFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;

public abstract class CoCategoryIndex<T extends CoCategory<T, ?>, M extends CoCategoryMask>
        extends CoIndex<T, M> {

    private String schema;

    public CoCategoryIndex(String schema) {
        this.schema = schema;
    }

    @Override
    protected void save(IVariantMap<String> q, T obj, AjaxResult result) {
        CoCategory<?, ?> cat = obj;
        RichProperties properties = cat.getProperties();
        if (properties != null) {
            List<ItemFile> images = properties.getImages();
            UploadFn.submitFiles(images, schema, lazyId(obj));
        }
        super.save(q, obj, result);
    }

}
