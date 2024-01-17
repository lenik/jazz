package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _UserSecretCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    /** The declaring user */
    Integer userId;
    IntegerRange userIdRange = new IntegerRange();

    /** Password data */
    String password;
    String passwordPattern;

    /** Protection question */
    String question;
    String questionPattern;

    /** Protection answer */
    String answer;
    String answerPattern;

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange range) {
        this.idRange = range;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp value) {
        this.creationDate = value;
    }

    public DateTimeRange getCreationDateRange() {
        return creationDateRange;
    }

    public void setCreationDateRange(DateTimeRange range) {
        this.creationDateRange = range;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp value) {
        this.lastModifiedDate = value;
    }

    public DateTimeRange getLastModifiedDateRange() {
        return lastModifiedDateRange;
    }

    public void setLastModifiedDateRange(DateTimeRange range) {
        this.lastModifiedDateRange = range;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer value) {
        this.version = value;
    }

    public IntegerRange getVersionRange() {
        return versionRange;
    }

    public void setVersionRange(IntegerRange range) {
        this.versionRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    /** The declaring user */
    public Integer getUserId() {
        return userId;
    }

    /** The declaring user */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    public IntegerRange getUserIdRange() {
        return userIdRange;
    }

    public void setUserIdRange(IntegerRange range) {
        this.userIdRange = range;
    }

    /** Password data */
    public String getPassword() {
        return password;
    }

    /** Password data */
    public void setPassword(String value) {
        this.password = value;
    }

    public String getPasswordPattern() {
        return passwordPattern;
    }

    public void setPasswordPattern(String value) {
        this.passwordPattern = value;
    }

    /** Protection question */
    public String getQuestion() {
        return question;
    }

    /** Protection question */
    public void setQuestion(String value) {
        this.question = value;
    }

    public String getQuestionPattern() {
        return questionPattern;
    }

    public void setQuestionPattern(String value) {
        this.questionPattern = value;
    }

    /** Protection answer */
    public String getAnswer() {
        return answer;
    }

    /** Protection answer */
    public void setAnswer(String value) {
        this.answer = value;
    }

    public String getAnswerPattern() {
        return answerPattern;
    }

    public void setAnswerPattern(String value) {
        this.answerPattern = value;
    }

}
