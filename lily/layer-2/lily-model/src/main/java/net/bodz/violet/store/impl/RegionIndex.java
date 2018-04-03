package net.bodz.violet.store.impl;

import java.util.List;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.file.UploadFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.RegionProperties;

/**
 * Region
 */
@ObjectType(Region.class)
public class RegionIndex
        extends CoIndex<Region, RegionMask> {

    static final String SCHEMA = "region";

    @Override
    protected void save(IVariantMap<String> q, Region obj, AjaxResult result) {
        RegionProperties properties = obj.getProperties();
        if (properties != null) {
            List<ItemFile> images = properties.getImages();
            UploadFn.submitFiles(images, SCHEMA, lazyId(obj));
        }
        super.save(q, obj, result);
    }

}
