package net.bodz.lily.entity.attachment;

import net.bodz.lily.entity.manager.RowOpListeners;

@RowOpListeners(UpdateAttachments.class)
public interface IHaveAttachments {

    IAttachmentListing listAttachments();

}
