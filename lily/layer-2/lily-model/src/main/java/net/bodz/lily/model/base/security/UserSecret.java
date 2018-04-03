package net.bodz.lily.model.base.security;

import net.bodz.bas.site.json.JsonMap;
import net.bodz.lily.model.base.CoEntity;

/**
 * 分拆至 usersec 为了区分存储。
 */
@Deprecated
public class UserSecret
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    int salt;
    String password;
    String publicKey;
    String email;
    boolean emailOk;
    String tel;
    boolean telOk;
    JsonMap properties = new JsonMap();

    String question;
    String answer;

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailOk() {
        return emailOk;
    }

    public void setEmailOk(boolean emailOk) {
        this.emailOk = emailOk;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isTelOk() {
        return telOk;
    }

    public void setTelOk(boolean telOk) {
        this.telOk = telOk;
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
