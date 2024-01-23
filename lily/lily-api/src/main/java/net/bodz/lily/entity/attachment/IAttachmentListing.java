package net.bodz.lily.entity.attachment;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import net.bodz.bas.t.iterator.PrefetchedIterator;

public interface IAttachmentListing
        extends
            IAttachmentPathChangeListener {

    String IMAGE = "image";
    String VIDEO = "video";
    String DOCUMENT = "document";

    default Collection<String> getAttachmentCategories() {
        return Arrays.asList(IMAGE);
    }

    Collection<IAttachment> getAttachments(String category);

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
    Iterator<String> categoryIterator;
    Iterator<IAttachment> attachmentIterator;

    public AttachmentIterator(IAttachmentListing listing) {
        this.listing = listing;
        this.categoryIterator = listing.getAttachmentCategories().iterator();
    }

    @Override
    protected IAttachment fetch() {
        if (attachmentIterator == null) {
            if (!categoryIterator.hasNext())
                return end();
            String category = categoryIterator.next();
            attachmentIterator = listing.getAttachments(category).iterator();
        }
        if (attachmentIterator.hasNext())
            return attachmentIterator.next();
        attachmentIterator = null;
        return fetch();
    }

}