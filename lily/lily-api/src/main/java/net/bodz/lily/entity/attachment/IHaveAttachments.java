package net.bodz.lily.entity.attachment;

import net.bodz.lily.entity.manager.RowOpAware;

@RowOpAware(UpdateAttachments.class)
public interface IHaveAttachments {

    IAttachmentListing listAttachments();

}
