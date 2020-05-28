package net.bodz.lily.security;

import java.util.Random;

import javax.persistence.Table;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 分拆至 usersec 为了区分存储。
 */
@Table(name = "usersec")
@IdType(Integer.class)
public class UserSecret
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_PASSWORD = 40;
    public static final int N_EMAIL = 30;
    public static final int N_MOBILE = 20;

    private User user;

    private static final Random RANDOM = new Random();
    private int salt = RANDOM.nextInt();
    private String password;

    private String publicKey;

    private JsonMap properties = new JsonMap();

    private String question;
    private String answer;

    public UserSecret user(User user) {
        setUser(user);
        return this;
    }

    public UserSecret password(String password) {
        setPassword(password);
        return this;
    }

    public UserSecret publicKey(String publicKey) {
        setPublicKey(publicKey);
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
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

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public JsonMap getProperties() {
        return properties;
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

}
