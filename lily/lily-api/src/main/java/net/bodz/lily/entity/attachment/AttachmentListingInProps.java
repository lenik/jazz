package net.bodz.lily.entity.attachment;

import java.util.Collection;

import net.bodz.lily.concrete.IAttachmentsInProps;

public class AttachmentListingInProps
        implements
            IAttachmentListing {

    IAttachmentsInProps attachmentsInProps;
    String[] attachmentGroupKeys;

    public AttachmentListingInProps(IAttachmentsInProps attachmentsInProps, String[] attachmentGroupKeys) {
        this.attachmentsInProps = attachmentsInProps;
        this.attachmentGroupKeys = attachmentGroupKeys;
    }

    @Override
    public String[] getAttachmentGroupKeys() {
        return attachmentGroupKeys;
    }

    @Override
    public Collection<IAttachment> getAttachmentGroup(String groupKey) {
        return attachmentsInProps.getAttachmentGroup(groupKey);
    }

    @Override
    public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
        event.getNewVolume();
        event.getNewPath();
    }

}
