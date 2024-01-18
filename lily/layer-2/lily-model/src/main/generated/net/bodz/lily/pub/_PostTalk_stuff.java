package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.template.CoTalk;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Long.class)
public abstract class _PostTalk_stuff<this_t extends _PostTalk_stuff<this_t>>
        extends CoTalk<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "post_msg";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_POST_ID = "post";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_POST_ID = 19;

    private static final int _ord_FORM_ARGUMENTS = 15;
    private static final int _ord_POST_ID = _ord_FORM_ARGUMENTS + 1;

    String formArguments;

    /**  */
    @NotNull
    Post post;

    @NotNull
    long postId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    /**
     *
     * @label post
     * @constraint foreign key (post) references lily.post (id)
     */
    @NotNull
    public Post getPost() {
        return post;
    }

    /**
     */
    public void setPost(@NotNull Post value) {
        this.post = value;
    }

    @Ordinal(_ord_POST_ID)
    @Precision(value = 19)
    @Column(name = "post", nullable = false, precision = 19)
    public synchronized long getPostId() {
        if (post != null) {
            if (post.getId() == null)
                return 0L;
            return post.getId();
        }
        return postId;
    }

    public synchronized void setPostId(long value) {
        this.postId = value;
    }

    public void initNotNulls() {
    }

}
