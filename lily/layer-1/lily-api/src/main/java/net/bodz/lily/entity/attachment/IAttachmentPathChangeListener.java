package net.bodz.lily.entity.attachment;

import java.util.EventListener;

public interface IAttachmentPathChangeListener
        extends
            EventListener {

    void onAttachmentPathChanged(AttachmentPathChangeEvent event);

}
