package net.bodz.lily.geo;

import java.util.Collection;

import javax.persistence.Table;

import net.bodz.bas.site.file.UploadHint;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.attachment.AttachmentPathChangeEvent;
import net.bodz.lily.entity.attachment.IAttachment;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.IHaveAttachments;
import net.bodz.lily.model.base.CoNode;
import net.bodz.lily.t.struct.GeoLocation;
import net.bodz.lily.template.RichProperties;

@Table(name = "zone")
@IdType(Integer.class)
@UploadHint
public class Zone
        extends CoNode<Zone, Integer>
        implements
            IHaveAttachments {

    private static final long serialVersionUID = 1L;

    public static final int ID_World = 0;
    public static final int ID_China = 86;

    private ZoneCategory category;
    private RichProperties properties = new RichProperties();
    private final GeoLocation geo = new GeoLocation();
    private String fullPath = "";

    public ZoneCategory getCategory() {
        return category;
    }

    public void setCategory(ZoneCategory category) {
        this.category = category;
    }

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
