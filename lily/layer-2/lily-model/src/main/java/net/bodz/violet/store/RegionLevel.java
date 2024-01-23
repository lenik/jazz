package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.bas.meta.res.HaveAttachments;


/**
 * 区域级别
 */
@HaveAttachments
@Table(schema = "violet", name = "regionlevel")
public class RegionLevel
        extends _RegionLevel_stuff<RegionLevel> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstracts = 0;
    public static final int ID_Place = 1;
    public static final int ID_Room = 2;
    public static final int ID_Shelf = 3;

}
