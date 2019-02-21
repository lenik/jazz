package net.bodz.lily.geo;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoCategory;

/**
 * @see Zone
 */
@Table(name = "zonecat")
@IdType(Integer.class)
public class ZoneCategory
        extends CoCategory<ZoneCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstract = 1;
    public static final int ID_Gov = 2;
    public static final int ID_Building = 3;
    public static final int ID_Floor = 4;
    public static final int ID_Room = 5;

    public ZoneCategory() {
    }

}
