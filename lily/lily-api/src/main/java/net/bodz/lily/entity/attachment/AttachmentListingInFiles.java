package net.bodz.lily.entity.attachment;

import java.util.Collection;

import net.bodz.lily.concrete.IAttachmentsInFiles;

public class AttachmentListingInFiles
        implements
            IAttachmentListing {

    IAttachmentsInFiles attachmentsInFiles;
    String[] attachmentGroupKeys;

    public AttachmentListingInFiles(IAttachmentsInFiles attachmentsInFiles, String[] attachmentGroupKeys) {
        this.attachmentsInFiles = attachmentsInFiles;
        this.attachmentGroupKeys = attachmentGroupKeys;
    }

    @Override
    public String[] getAttachmentGroupKeys() {
        return attachmentGroupKeys;
    }

    @Override
    public Collection<IAttachment> getAttachmentGroup(String groupKey) {
        return attachmentsInFiles.getAttachmentGroup(groupKey);
    }

    @Override
    public void onAttachmentPathChanged(AttachmentPathChangeEvent event) {
        event.getNewVolume();
        event.getNewPath();
    }

}
