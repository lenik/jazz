package net.bodz.lily.entity.attachment;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;
import net.bodz.bas.std.rfc.mime.ContentType;

public interface IHaveDocs<A extends MutableAttachment>
        extends IHaveMutableAttachments<A> {

    Predicate<IAttachment> TYPE_DOC = a -> //
            ContentType.forExtension(a.getExtension(), ContentType.DEFAULT).isText();

    @NotNull
    default List<A> getDocs() {
        return filter(TYPE_DOC);
    }

    default void setDocs(Collection<? extends A> replacement) {
        filter(TYPE_DOC, replacement);
    }

    @Nullable
    default A getDefaultDoc() {
        return find(TYPE_DOC);
    }

    default void setDefaultDoc(A doc) {
        find(TYPE_DOC, doc);
    }

}