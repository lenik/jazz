package net.bodz.lily.schema.pub;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _Article_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "article";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_FAV_COUNT = "nfav";
    public static final String FIELD_VOTE_COUNT = "nvote";
    public static final String FIELD_HATE_COUNT = "nhate";
    public static final String FIELD_MESSAGE_COUNT = "nmsg";
    public static final String FIELD_PLUGINS = "plugins";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_FAV_COUNT = 10;
    public static final int N_VOTE_COUNT = 10;
    public static final int N_HATE_COUNT = 10;
    public static final int N_MESSAGE_COUNT = 10;
    public static final int N_PLUGINS = 2147483647;

    private static final int _ord_FORM_ARGUMENTS = 22;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_FAV_COUNT = _ord_CATEGORY_ID + 3;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_MESSAGE_COUNT = _ord_HATE_COUNT + 1;
    private static final int _ord_PLUGINS = _ord_MESSAGE_COUNT + 1;

    String formArguments;

    @NotNull
    int favCount;

    @NotNull
    int voteCount;

    @NotNull
    int hateCount;

    @NotNull
    int messageCount;

    JsonVariant plugins;

    /**  */
    ArticleCategory category;

    Integer categoryId;

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

    @Ordinal(_ord_FAV_COUNT)
    @Precision(value = 10)
    @Column(name = "nfav", nullable = false, precision = 10)
    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int value) {
        this.favCount = value;
    }

    @Ordinal(_ord_VOTE_COUNT)
    @Precision(value = 10)
    @Column(name = "nvote", nullable = false, precision = 10)
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int value) {
        this.voteCount = value;
    }

    @Ordinal(_ord_HATE_COUNT)
    @Precision(value = 10)
    @Column(name = "nhate", nullable = false, precision = 10)
    public int getHateCount() {
        return hateCount;
    }

    public void setHateCount(int value) {
        this.hateCount = value;
    }

    @Ordinal(_ord_MESSAGE_COUNT)
    @Precision(value = 10)
    @Column(name = "nmsg", nullable = false, precision = 10)
    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int value) {
        this.messageCount = value;
    }

    @Ordinal(_ord_PLUGINS)
    @Precision(value = 2147483647)
    @Column(name = "plugins", precision = 2147483647)
    public JsonVariant getPlugins() {
        return plugins;
    }

    public void setPlugins(JsonVariant value) {
        this.plugins = value;
    }

    /**
     *
     * @constraint foreign key (cat) references lily.articlecat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    public ArticleCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(ArticleCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    public void initNotNulls() {
    }

}
