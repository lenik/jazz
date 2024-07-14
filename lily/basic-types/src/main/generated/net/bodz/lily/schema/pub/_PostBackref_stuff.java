package net.bodz.lily.schema.pub;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.BackrefRecord;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.inet.ExternalSite;

@IdType(Long.class)
public abstract class _PostBackref_stuff
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "post_backref";

    public static final String FIELD_POST_ID = "post";
    public static final String FIELD_SITE_ID = "site";
    public static final String FIELD_KEY = "key";
    public static final String FIELD_VALUE = "value";

    public static final int N_POST_ID = 19;
    public static final int N_SITE_ID = 10;
    public static final int N_KEY = 30;
    public static final int N_VALUE = 10;

    private static final int _ord_POST_ID = 15;
    private static final int _ord_SITE_ID = _ord_POST_ID + 1;
    private static final int _ord_KEY = _ord_SITE_ID + 1;
    private static final int _ord_VALUE = _ord_KEY + 1;

    String key;

    @NotNull
    int value;

    /**  */
    @NotNull
    Post post;

    @NotNull
    long postId;

    /**  */
    @NotNull
    ExternalSite site;

    @NotNull
    int siteId;

    @Ordinal(_ord_KEY)
    @Precision(value = N_KEY)
    @TextInput(maxLength = N_KEY)
    @Column(name = "key", length = N_KEY)
    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = 10)
    @Column(name = "value", nullable = false, precision = 10)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @constraint foreign key (post) references lily.post (id)
     */
    @JoinColumn(name = "post")
    @ManyToOne
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

    /**
     *
     * @constraint foreign key (site) references lily.extsite (id)
     */
    @JoinColumn(name = "site")
    @ManyToOne
    @NotNull
    public ExternalSite getSite() {
        return site;
    }

    /**
     */
    public void setSite(@NotNull ExternalSite value) {
        this.site = value;
    }

    @Ordinal(_ord_SITE_ID)
    @Precision(value = 10)
    @Column(name = "site", nullable = false, precision = 10)
    public synchronized int getSiteId() {
        if (site != null) {
            if (site.getId() == null)
                return 0;
            return site.getId();
        }
        return siteId;
    }

    public synchronized void setSiteId(int value) {
        this.siteId = value;
    }

    public void initNotNulls() {
    }

}
