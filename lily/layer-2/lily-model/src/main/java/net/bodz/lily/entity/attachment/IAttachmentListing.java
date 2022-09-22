package net.bodz.lily.entity.attachment;

import java.util.Collection;

public interface IAttachmentListing
        extends
            IAttachmentPathChangeListener {

    Collection<String> getAttachmentCategories();

    Collection<IAttachment> getAttachments(String category);

}
