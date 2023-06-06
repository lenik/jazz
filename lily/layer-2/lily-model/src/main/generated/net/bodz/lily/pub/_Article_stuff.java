package net.bodz.lily.pub;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
public abstract class _Article_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;

    private static final int _ord_YEAR = 16;
    private static final int _ord_FORM_ARGUMENTS = _ord_YEAR + 5;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_FAV_COUNT = _ord_CATEGORY_ID + 2;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_NMSG = _ord_HATE_COUNT + 1;
    private static final int _ord_PLUGINS = _ord_NMSG + 1;

    @NotNull
    int year;

    String formArguments;

    @NotNull
    int favCount;

    @NotNull
    int voteCount;

    @NotNull
    int hateCount;

    @NotNull
    int nmsg;

    Object plugins;

    /**  */
    ArticleCategory category;

    Integer categoryId;

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

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

    @Ordinal(_ord_NMSG)
    @Precision(value = 10)
    @Column(name = "nmsg", nullable = false, precision = 10)
    public int getNmsg() {
        return nmsg;
    }

    public void setNmsg(int value) {
        this.nmsg = value;
    }

    @Ordinal(_ord_PLUGINS)
    @Precision(value = 2147483647)
    @Column(name = "plugins", precision = 2147483647)
    public Object getPlugins() {
        return plugins;
    }

    public void setPlugins(Object value) {
        this.plugins = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references lily.articlecat (id)
     */
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
