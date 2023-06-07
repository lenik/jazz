package net.bodz.violet.edu;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _Course_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_CATEGORY_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_CATEGORY_ID = 15;
    private static final int _ord_FAV_COUNT = _ord_CATEGORY_ID + 1;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_CREDIT = _ord_HATE_COUNT + 1;
    private static final int _ord_PLUGINS = _ord_CREDIT + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    int favCount;

    @NotNull
    int voteCount;

    @NotNull
    int hateCount;

    @NotNull
    int credit;

    Object plugins;

    /**  */
    CourseCategory category;

    Integer categoryId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
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

    @Ordinal(_ord_CREDIT)
    @Precision(value = 10)
    @Column(name = "credit", nullable = false, precision = 10)
    public int getCredit() {
        return credit;
    }

    public void setCredit(int value) {
        this.credit = value;
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
     * @constraint foreign key (cat) references violet.coursecat (id)
     */
    public CourseCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(CourseCategory value) {
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
