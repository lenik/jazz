package net.bodz.lily.schema.geo;

import java.util.Collection;

import javax.persistence.Table;

import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.concrete.RichProperties;
import net.bodz.lily.concrete.util.GeoLocation;
import net.bodz.lily.entity.attachment.AttachmentPathChangeEvent;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.IHaveAttachments;

@HaveAttachments
@Table(schema = "lily", name = "zone")
public class Zone
        extends _Zone_stuff
        implements
            IHaveAttachments {

    private static final long serialVersionUID = 1L;

    public static final int ID_World = 0;
    public static final int ID_China = 86;

    private RichProperties properties = new RichProperties();

    public final GeoLocation geo = new GeoLocation();
    private String fullPath = "";

    @Override
    public RichProperties getProperties() {
        return properties;
    }

    public void setProperties(RichProperties properties) {
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

    @Override
    public IAttachmentListing listAttachments() {
        return new IAttachmentListing() {

            @Override
            public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
                event.getNewVolume();
                event.getNewPath();
            }

            @Override
            public Collection<IAttachment> getAttachments(String category) {
                switch (category) {
                case IMAGE:
                    return properties.getImages();
                }
                return null;
            }
        };
    }

}
