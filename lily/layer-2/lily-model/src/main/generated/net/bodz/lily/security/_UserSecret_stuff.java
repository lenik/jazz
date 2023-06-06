package net.bodz.lily.security;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * User Secret
 */
@IdType(Integer.class)
public abstract class _UserSecret_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_PASSWD = 40;
    public static final int N_QUESTION = 100;
    public static final int N_ANSWER = 30;

    private static final int _ord_ID = 1;
    private static final int _ord_USER_ID = _ord_ID + 5;
    private static final int _ord_PASSWD = _ord_USER_ID + 1;
    private static final int _ord_QUESTION = _ord_PASSWD + 1;
    private static final int _ord_ANSWER = _ord_QUESTION + 1;

    @Id
    @NotNull
    int id;

    /** Password data */
    @NotNull
    String passwd;

    /** Protection question */
    String question;

    /** Protection answer */
    String answer;

    /** The declaring user */
    @NotNull
    User user;

    /** The declaring user */
    @NotNull
    int userId;

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

    /**
     * Password data
     */
    @Ordinal(_ord_PASSWD)
    @NotNull
    @Precision(value = N_PASSWD)
    @TextInput(maxLength = N_PASSWD)
    @Column(name = "passwd", nullable = false, length = N_PASSWD)
    public String getPasswd() {
        return passwd;
    }

    /**
     * Password data
     */
    public void setPasswd(@NotNull String value) {
        this.passwd = value;
    }

    /**
     * Protection question
     */
    @Ordinal(_ord_QUESTION)
    @Precision(value = N_QUESTION)
    @TextInput(maxLength = N_QUESTION)
    @Column(name = "question", length = N_QUESTION)
    public String getQuestion() {
        return question;
    }

    /**
     * Protection question
     */
    public void setQuestion(String value) {
        this.question = value;
    }

    /**
     * Protection answer
     */
    @Ordinal(_ord_ANSWER)
    @Precision(value = N_ANSWER)
    @TextInput(maxLength = N_ANSWER)
    @Column(name = "answer", length = N_ANSWER)
    public String getAnswer() {
        return answer;
    }

    /**
     * Protection answer
     */
    public void setAnswer(String value) {
        this.answer = value;
    }

    /**
     * The declaring user
     *
     * @label user
     * @constraint foreign key (user) references lily.user (id)
     */
    @NotNull
    public User getUser() {
        return user;
    }

    /**
     * The declaring user
     */
    public void setUser(@NotNull User value) {
        this.user = value;
    }

    /**
     * The declaring user
     */
    @Ordinal(_ord_USER_ID)
    @Precision(value = 10)
    @Column(name = "user", nullable = false, precision = 10)
    public synchronized int getUserId() {
        if (user != null) {
            if (user.getId() == null)
                return 0;
            return user.getId();
        }
        return userId;
    }

    /**
     * The declaring user
     */
    public synchronized void setUserId(int value) {
        this.userId = value;
    }

    public void initNotNulls() {
        this.passwd = "";
    }

}
