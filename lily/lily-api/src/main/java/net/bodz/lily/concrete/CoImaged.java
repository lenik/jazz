package net.bodz.lily.concrete;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.res.HaveAttachments;
import net.bodz.lily.entity.attachment.AttachmentListingInProps;
import net.bodz.lily.entity.attachment.IAttachmentListing;
import net.bodz.lily.entity.attachment.util.IImagesInProps;
import net.bodz.lily.entity.esm.DTColumn;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.ID_TYPE })
@HaveAttachments
public class CoImaged<Id>
        extends IdEntity<Id>
        implements
            IImagesInProps {

    private static final long serialVersionUID = 1L;

    private JsonVariant properties;

    @DTColumn(hidden = true)
    @Override
    public JsonVariant getProperties() {
        return properties;
    }

    @Override
    public void setProperties(JsonVariant properties) {
        this.properties = properties;
    }

    static final String[] attachmentGroupKeys = { //
            K_IMAGES, //
    };

    @Override
    public IAttachmentListing listAttachments() {
        return new AttachmentListingInProps(this, attachmentGroupKeys);
    }

}
