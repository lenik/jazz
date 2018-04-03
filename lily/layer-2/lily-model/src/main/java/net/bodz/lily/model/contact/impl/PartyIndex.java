package net.bodz.lily.model.contact.impl;

import java.util.List;

import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.file.UploadFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.model.base.CoObjectMask;
import net.bodz.lily.model.contact.Party;
import net.bodz.lily.model.contact.PartyProperties;

public class PartyIndex<T extends Party, M extends CoObjectMask>
        extends CoIndex<T, M> {

    private String schema;

    public PartyIndex(String schema) {
        this.schema = schema;
    }

    @Override
    protected void save(IVariantMap<String> q, T obj, AjaxResult result) {
        PartyProperties properties = obj.getProperties();
        if (properties != null) {
            List<ItemFile> images = properties.getImages();
            UploadFn.submitFiles(images, schema, lazyId(obj));
        }
        super.save(q, obj, result);
    }

}
