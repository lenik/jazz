package net.bodz.lily.security;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

/**
 * 分拆至 usersec 为了区分存储。
 */
@Table(name = "usersec")
@IdType(Integer.class)
public class UserSecret
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_PASSWORD = 40;
    public static final int N_EMAIL = 30;
    public static final int N_MOBILE = 20;

    private User user;
    private String password;
    private String question;
    private String answer;

    private JsonMap properties = new JsonMap();

    public UserSecret user(User user) {
        setUser(user);
        return this;
    }

    public UserSecret password(String password) {
        setPassword(password);
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @TextInput(maxLength = N_PASSWORD)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordMatched(String s) {
        if (password == null || s == null)
            return false;
        return password.equals(s);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public JsonMap getProperties() {
        return properties;
    }

}
