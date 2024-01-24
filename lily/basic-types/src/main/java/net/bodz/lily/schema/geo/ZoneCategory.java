package net.bodz.lily.schema.geo;

import javax.persistence.Table;


/**
 * @see Zone
 */
@Table(schema = "lily", name = "zonecat")
public class ZoneCategory
        extends _ZoneCategory_stuff {

    private static final long serialVersionUID = 1L;

    public static final int ID_Abstract = 1;
    public static final int ID_Gov = 2;
    public static final int ID_Building = 3;
    public static final int ID_Floor = 4;
    public static final int ID_Room = 5;
}
