package net.bodz.lily.schema.contact;

import javax.persistence.Column;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Person_stuff
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "person";

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_FATHER_ID = "father";
    public static final String FIELD_MOTHER_ID = "mother";
    public static final String FIELD_ROLE_COUNT = "nrole";
    public static final String FIELD_EMPLOYEE = "employee";
    public static final String FIELD_BANK_COUNT = "nbank";
    public static final String FIELD_GENDER = "gender";
    public static final String FIELD_PRONOUN = "pronoun";
    public static final String FIELD_SEXUAL_ORIENTATION = "sexual_orient";
    public static final String FIELD_HOMELAND = "homeland";
    public static final String FIELD_PASSPORT = "passport";
    public static final String FIELD_SSN = "ssn";
    public static final String FIELD_DLN = "dln";

    public static final int N_PROPERTIES = 2147483647;
    public static final int N_FATHER_ID = 10;
    public static final int N_MOTHER_ID = 10;
    public static final int N_ROLE_COUNT = 10;
    public static final int N_EMPLOYEE = 1;
    public static final int N_BANK_COUNT = 10;
    public static final int N_GENDER = 1;
    public static final int N_PRONOUN = 30;
    public static final int N_SEXUAL_ORIENTATION = 30;
    public static final int N_HOMELAND = 10;
    public static final int N_PASSPORT = 20;
    public static final int N_SSN = 20;
    public static final int N_DLN = 20;

    private static final int _ord_PROPERTIES = 24;
    private static final int _ord_FATHER_ID = _ord_PROPERTIES + 3;
    private static final int _ord_MOTHER_ID = _ord_FATHER_ID + 1;
    private static final int _ord_ROLE_COUNT = _ord_MOTHER_ID + 3;
    private static final int _ord_EMPLOYEE = _ord_ROLE_COUNT + 3;
    private static final int _ord_BANK_COUNT = _ord_EMPLOYEE + 2;
    private static final int _ord_GENDER = _ord_BANK_COUNT + 1;
    private static final int _ord_PRONOUN = _ord_GENDER + 1;
    private static final int _ord_SEXUAL_ORIENTATION = _ord_PRONOUN + 1;
    private static final int _ord_HOMELAND = _ord_SEXUAL_ORIENTATION + 1;
    private static final int _ord_PASSPORT = _ord_HOMELAND + 1;
    private static final int _ord_SSN = _ord_PASSPORT + 1;
    private static final int _ord_DLN = _ord_SSN + 1;

    JsonVariant properties;

    @NotNull
    int roleCount;

    @NotNull
    boolean employee;

    @NotNull
    int bankCount;

    Gender gender;

    String pronoun;

    String sexualOrientation;

    String homeland;

    String passport;

    String ssn;

    String dln;

    /**  */
    Person mother;

    Integer motherId;

    /**  */
    Person father;

    Integer fatherId;

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    @Ordinal(_ord_ROLE_COUNT)
    @Precision(value = 10)
    @Column(name = "nrole", nullable = false, precision = 10)
    public int getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(int value) {
        this.roleCount = value;
    }

    @Ordinal(_ord_EMPLOYEE)
    @Precision(value = 1)
    @Column(name = "employee", nullable = false, precision = 1)
    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean value) {
        this.employee = value;
    }

    @Ordinal(_ord_BANK_COUNT)
    @Precision(value = 10)
    @Column(name = "nbank", nullable = false, precision = 10)
    public int getBankCount() {
        return bankCount;
    }

    public void setBankCount(int value) {
        this.bankCount = value;
    }

    @Ordinal(_ord_GENDER)
    @Precision(value = 1)
    @Column(name = "gender", precision = 1)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender value) {
        this.gender = value;
    }

    @Ordinal(_ord_PRONOUN)
    @Precision(value = N_PRONOUN)
    @TextInput(maxLength = N_PRONOUN)
    @Column(name = "pronoun", length = N_PRONOUN)
    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String value) {
        this.pronoun = value;
    }

    @Ordinal(_ord_SEXUAL_ORIENTATION)
    @Precision(value = N_SEXUAL_ORIENTATION)
    @TextInput(maxLength = N_SEXUAL_ORIENTATION)
    @Column(name = "sexual_orient", length = N_SEXUAL_ORIENTATION)
    public String getSexualOrientation() {
        return sexualOrientation;
    }

    public void setSexualOrientation(String value) {
        this.sexualOrientation = value;
    }

    @Ordinal(_ord_HOMELAND)
    @Precision(value = N_HOMELAND)
    @TextInput(maxLength = N_HOMELAND)
    @Column(name = "homeland", length = N_HOMELAND)
    public String getHomeland() {
        return homeland;
    }

    public void setHomeland(String value) {
        this.homeland = value;
    }

    @Ordinal(_ord_PASSPORT)
    @Precision(value = N_PASSPORT)
    @TextInput(maxLength = N_PASSPORT)
    @Column(name = "passport", length = N_PASSPORT)
    public String getPassport() {
        return passport;
    }

    public void setPassport(String value) {
        this.passport = value;
    }

    @Ordinal(_ord_SSN)
    @Precision(value = N_SSN)
    @TextInput(maxLength = N_SSN)
    @Column(name = "ssn", length = N_SSN)
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String value) {
        this.ssn = value;
    }

    @Ordinal(_ord_DLN)
    @Precision(value = N_DLN)
    @TextInput(maxLength = N_DLN)
    @Column(name = "dln", length = N_DLN)
    public String getDln() {
        return dln;
    }

    public void setDln(String value) {
        this.dln = value;
    }

    /**
     *
     * @constraint foreign key (mother) references lily.person (id)
     */
    public Person getMother() {
        return mother;
    }

    /**
     */
    public void setMother(Person value) {
        this.mother = value;
    }

    @Ordinal(_ord_MOTHER_ID)
    @Precision(value = N_MOTHER_ID)
    @Column(name = "mother", precision = 10)
    public synchronized Integer getMotherId() {
        if (mother != null) {
            return mother.getId();
        }
        return motherId;
    }

    public synchronized void setMotherId(Integer value) {
        this.motherId = value;
    }

    /**
     *
     * @constraint foreign key (father) references lily.person (id)
     */
    public Person getFather() {
        return father;
    }

    /**
     */
    public void setFather(Person value) {
        this.father = value;
    }

    @Ordinal(_ord_FATHER_ID)
    @Precision(value = N_FATHER_ID)
    @Column(name = "father", precision = 10)
    public synchronized Integer getFatherId() {
        if (father != null) {
            return father.getId();
        }
        return fatherId;
    }

    public synchronized void setFatherId(Integer value) {
        this.fatherId = value;
    }

    public void initNotNulls() {
    }

}
