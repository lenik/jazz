package net.bodz.lily.entity.attachment;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public interface IAttachmentListing
        extends
            IAttachmentPathChangeListener {

    String[] getAttachmentGroupKeys();

    Collection<IAttachment> getAttachmentGroup(String groupKey);

    default Iterable<IAttachment> getAttachments() {
        return () -> new AttachmentIterator(this);
    }

    default IAttachment getAttachment(int index) {
        int pos = 0;
        for (IAttachment attachment : getAttachments()) {
            if (pos == index)
                return attachment;
            pos++;
        }
        return null;
    }

    default IAttachment attachment(int index) {
        int pos = 0;
        for (IAttachment attachment : getAttachments()) {
            if (pos == index)
                return attachment;
            pos++;
        }
        throw new IndexOutOfBoundsException(String.format(//
                "bad index %d: size %d", index, pos));
    }

}

class AttachmentIterator
        extends PrefetchedIterator<IAttachment> {

    IAttachmentListing listing;
    Iterator<String> groupKeyIterator;
    Iterator<IAttachment> attachmentIterator;

    public AttachmentIterator(IAttachmentListing listing) {
        this.listing = listing;
        String[] keys = listing.getAttachmentGroupKeys();
        this.groupKeyIterator = Arrays.asList(keys).iterator();
    }

    @Override
    protected IAttachment fetch() {
        if (attachmentIterator == null) {
            if (! groupKeyIterator.hasNext())
                return end();
            String category = groupKeyIterator.next();
            attachmentIterator = listing.getAttachmentGroup(category).iterator();
        }
        if (attachmentIterator.hasNext())
            return attachmentIterator.next();
        attachmentIterator = null;
        return fetch();
    }

}