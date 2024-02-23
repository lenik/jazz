package net.bodz.lily.schema.geo;

import java.util.Collection;

import javax.persistence.Table;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.concrete.util.GeoLocation;
import net.bodz.lily.entity.attachment.AttachmentPathChangeEvent;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentListing;
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

    JsonVariant properties;

    public final GeoLocation geo = new GeoLocation();
    private String fullPath = "";

    @Override
    public JsonVariant getProperties() {
        return properties;
    }

    @Override
    public void setProperties(JsonVariant properties) {
        this.properties = properties;
    }

    public GeoLocation getGeo() {
        return geo;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    static String[] attachmentGroupKeys = { K_IMAGES };

    @Override
    public IAttachmentListing listAttachments() {
        return new IAttachmentListing() {

            @Override
            public String[] getAttachmentGroupKeys() {
                return attachmentGroupKeys;
            }

            @Override
            public Collection<IAttachment> getAttachmentGroup(String groupKey) {
                return Zone.this.getAttachmentGroup(groupKey);
            }

            @Override
            public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
                event.getNewVolume();
                event.getNewPath();
            }

        };
    }

}
