package net.bodz.lily.pub;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.User;

@IdType(Long.class)
public abstract class _Post_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;
    public static final int N_OP_ID = 10;
    public static final int N_RAW_TEXT = 2147483647;
    public static final int N_FORM_ID = 10;
    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_PARENT_ID = 10;
    public static final int N_CATEGORY_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = 12;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_SUBJECT = _ord_YEAR + 1;
    private static final int _ord_OP_ID = _ord_SUBJECT + 1;
    private static final int _ord_RAW_TEXT = _ord_OP_ID + 1;
    private static final int _ord_FORM_ID = _ord_RAW_TEXT + 1;
    private static final int _ord_FORM_ARGUMENTS = _ord_FORM_ID + 1;
    private static final int _ord_PARENT_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_CATEGORY_ID = _ord_PARENT_ID + 1;
    private static final int _ord_FAV_COUNT = _ord_CATEGORY_ID + 1;
    private static final int _ord_VOTE_COUNT = _ord_FAV_COUNT + 1;
    private static final int _ord_HATE_COUNT = _ord_VOTE_COUNT + 1;
    private static final int _ord_NMSG = _ord_HATE_COUNT + 1;
    private static final int _ord_PLUGINS = _ord_NMSG + 1;

    @Id
    @NotNull
    long id;

    Timestamp beginTime;

    Timestamp endTime;

    @NotNull
    int year;

    @NotNull
    String subject;

    String rawText;

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
    Post parent;

    Integer parentId;

    /** (User Account) */
    User op;

    Integer opId;

    /**  */
    FormDef form;

    Integer formId;

    /**  */
    PostCategory category;

    Integer categoryId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_SUBJECT)
    @NotNull
    @Precision(value = N_SUBJECT)
    @TextInput(maxLength = N_SUBJECT)
    @Column(name = "subject", nullable = false, length = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String value) {
        this.subject = value;
    }

    @Ordinal(_ord_RAW_TEXT)
    @Precision(value = N_RAW_TEXT)
    @TextInput(maxLength = N_RAW_TEXT)
    @Column(name = "text", length = N_RAW_TEXT)
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String value) {
        this.rawText = value;
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
     * @label parent
     * @constraint foreign key (parent) references lily.post (id)
     */
    public Post getParent() {
        return parent;
    }

    /**
     */
    public void setParent(Post value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 10)
    public synchronized Integer getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Integer value) {
        this.parentId = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @label op
     * @constraint foreign key (op) references lily.user (id)
     */
    public User getOp() {
        return op;
    }

    /**
     * User Account
     */
    public void setOp(User value) {
        this.op = value;
    }

    @Ordinal(_ord_OP_ID)
    @Precision(value = N_OP_ID)
    @Column(name = "op", precision = 10)
    public synchronized Integer getOpId() {
        if (op != null) {
            return op.getId();
        }
        return opId;
    }

    public synchronized void setOpId(Integer value) {
        this.opId = value;
    }

    /**
     *
     * @label form
     * @constraint foreign key (form) references lily._form (id)
     */
    public FormDef getForm() {
        return form;
    }

    /**
     */
    public void setForm(FormDef value) {
        this.form = value;
    }

    @Ordinal(_ord_FORM_ID)
    @Precision(value = N_FORM_ID)
    @Column(name = "form", precision = 10)
    public synchronized Integer getFormId() {
        if (form != null) {
            return form.getId();
        }
        return formId;
    }

    public synchronized void setFormId(Integer value) {
        this.formId = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references lily.postcat (id)
     */
    public PostCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(PostCategory value) {
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
        this.subject = "";
    }

}
