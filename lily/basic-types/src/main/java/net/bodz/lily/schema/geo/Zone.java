package net.bodz.lily.schema.geo;

import javax.persistence.Table;

import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.concrete.util.GeoLocation;
import net.bodz.lily.entity.attachment.util.IImagesInProps;

@HaveAttachments
@Table(schema = Zone.SCHEMA_NAME, name = Zone.TABLE_NAME)
public class Zone
        extends _Zone_stuff
        implements
            IImagesInProps {

    private static final long serialVersionUID = 1L;

    public static final int ID_World = 0;
    public static final int ID_China = 86;

    public final GeoLocation geo = new GeoLocation();
    private String fullPath = "";

    public GeoLocation getGeo() {
        return geo;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

}
