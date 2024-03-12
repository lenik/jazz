package net.bodz.lily.entity.attachment.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.attachment.IAttachment;

public interface IDocAttachments {

    List<IAttachment> getDocuments();

    void setDocuments(List<IAttachment> attachments);

    @Derived
    default IAttachment getDocument() {
        List<IAttachment> documents = getDocuments();
        if (documents == null || documents.isEmpty())
            return null;
        else
            return documents.get(0);
    }

    default void setDocument(IAttachment document) {
        List<IAttachment> documents = getDocuments();
        if (documents == null) {
            documents = new ArrayList<>();
            setDocuments(documents);
        }
        if (documents.isEmpty())
            documents.add(document);
        else
            documents.set(0, document);
    }

}
