package net.bodz.lily.security.impl;

import java.util.List;

import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.file.UploadFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.CoPrincipal;
import net.bodz.lily.security.CoPrincipalProperties;

public class CoPrincipalIndex<T extends CoPrincipal, M extends CoPrincipalMask>
        extends CoIndex<T, M> {

    private String schema;

    public CoPrincipalIndex(String schema) {
        this.schema = schema;
    }

    @Override
    protected void save(IVariantMap<String> q, T obj, AjaxResult result) {
        CoPrincipalProperties properties = obj.getProperties();
        if (properties != null) {
            List<ItemFile> images = properties.getImages();
            UploadFn.submitFiles(images, schema, lazyId(obj));
        }
        super.save(q, obj, result);
    }

}
