package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.bas.site.file.UploadHint;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;
import net.bodz.lily.template.RichProperties;

/**
 * 区域级别
 */
@IdType(Integer.class)
@Table(name = "regionlevel")
@UploadHint
public class RegionLevel
        extends CoCode<RegionLevel> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstracts = 0;
    public static final int ID_Place = 1;
    public static final int ID_Room = 2;
    public static final int ID_Shelf = 3;

    RichProperties properties = new RichProperties();

    @Override
    public RichProperties getProperties() {
        return properties;
    }

}
