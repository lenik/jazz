package net.bodz.lily.schema.account;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

/**
 * User Secret
 */
@IdType(Integer.class)
public abstract class _UserSecret_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "usersec";

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_USER_ID = "user";
    public static final String FIELD_PASSWORD = "passwd";
    public static final String FIELD_QUESTION = "question";
    public static final String FIELD_ANSWER = "answer";

    public static final int N_PROPERTIES = 2147483647;
    public static final int N_USER_ID = 10;
    public static final int N_PASSWORD = 40;
    public static final int N_QUESTION = 100;
    public static final int N_ANSWER = 30;

    private static final int _ord_PROPERTIES = 5;
    private static final int _ord_USER_ID = _ord_PROPERTIES + 1;
    private static final int _ord_PASSWORD = _ord_USER_ID + 1;
    private static final int _ord_QUESTION = _ord_PASSWORD + 1;
    private static final int _ord_ANSWER = _ord_QUESTION + 1;

    JsonVariant properties;

    /** Password data */
    @NotNull
    String password;

    /** Recover Question */
    String question;

    /** Recover Answer */
    String answer;

    /** The declaring user */
    @NotNull
    User user;

    /** The declaring user */
    @NotNull
    int userId;

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    /**
     * Password data
     */
    @Ordinal(_ord_PASSWORD)
    @NotNull
    @Precision(value = N_PASSWORD)
    @TextInput(maxLength = N_PASSWORD)
    @Column(name = "passwd", nullable = false, length = N_PASSWORD)
    public String getPassword() {
        return password;
    }

    /**
     * Password data
     */
    public void setPassword(@NotNull String value) {
        this.password = value;
    }

    /**
     * Recover Question
     */
    @Ordinal(_ord_QUESTION)
    @Precision(value = N_QUESTION)
    @TextInput(maxLength = N_QUESTION)
    @Column(name = "question", length = N_QUESTION)
    public String getQuestion() {
        return question;
    }

    /**
     * Recover Question
     */
    public void setQuestion(String value) {
        this.question = value;
    }

    /**
     * Recover Answer
     */
    @Ordinal(_ord_ANSWER)
    @Precision(value = N_ANSWER)
    @TextInput(maxLength = N_ANSWER)
    @Column(name = "answer", length = N_ANSWER)
    public String getAnswer() {
        return answer;
    }

    /**
     * Recover Answer
     */
    public void setAnswer(String value) {
        this.answer = value;
    }

    /**
     * The declaring user
     *
     * @label User
     * @constraint foreign key (user) references lily.user (id)
     */
    @JoinColumn(name = "user")
    @ManyToOne
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
        this.password = "";
    }

}
