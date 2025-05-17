package net.bodz.lily.schema.contact;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.repr.EntGroup;

public interface IContactAddress
        extends
            IJsonForm {

    int N_COUNTRY = 2;
    int N_R1 = 30;
    int N_R2 = 30;
    int N_R3 = 30;
    int N_R4 = 40;
    int N_ADDRESS1 = 80;
    int N_ADDRESS2 = 80;
    int N_POSTAL_CODE = 8;

    /**
     * 国家
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_COUNTRY)
    String getCountry();

    void setCountry(String country);

    /**
     * 省/直辖市
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_R1)
    String getR1();

    void setR1(String r1);

    /**
     * 市/地区
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_R2)
    String getR2();

    void setR2(String r2);

    /**
     * 县/府
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_R3)
    String getR3();

    void setR3(String r3);

    /**
     * 城镇/乡/街道
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_R4)
    String getR4();

    void setR4(String r4);

    /**
     * 村、街巷、路等。
     *
     * @label 地址1
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_ADDRESS1)
    String getAddress1();

    void setAddress1(String address1);

    /**
     * 建筑、楼层等。
     *
     * @label 地址2
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_ADDRESS2)
    String getAddress2();

    void setAddress2(String address2);

    /**
     * @label 邮政编码
     */
    @OfGroup(EntGroup.Position.class)
    @TextInput(maxLength = N_POSTAL_CODE)
    String getPostalCode();

    void setPostalCode(String postalCode);

    default String format(boolean topDown) {
        String country = getCountry();
        String r1 = getR1();
        String r2 = getR2();
        String r3 = getR3();
        String r4 = getR4();
        String address1 = getAddress1();
        String address2 = getAddress2();
        String postalCode = getPostalCode();

        StringBuilder sb = new StringBuilder();
        if (topDown) {
            if (country != null)
                sb.append('\n' + country);
            if (r1 != null)
                sb.append('\n' + r1);
            if (r2 != null)
                sb.append('\n' + r2);
            if (r3 != null)
                sb.append('\n' + r3);
            if (r4 != null)
                sb.append('\n' + r4);
            if (address1 != null)
                sb.append('\n' + address1);
            if (address2 != null)
                sb.append('\n' + address2);
        } else {
            if (address2 != null)
                sb.append('\n' + address2);
            if (address1 != null)
                sb.append('\n' + address1);
            if (r4 != null)
                sb.append('\n' + r4);
            if (r3 != null)
                sb.append('\n' + r3);
            if (r2 != null)
                sb.append('\n' + r2);
            if (r1 != null)
                sb.append('\n' + r1);
            if (country != null)
                sb.append('\n' + country);
        }
        if (postalCode != null)
            sb.append("\nPostal Code: " + postalCode);
        if (sb.length() == 0)
            return "";
        else
            return sb.substring(1);
    }

    @Override
    default void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        setCountry(o.getString("country", getCountry()));
        setR1(o.getString("r1", getR1()));
        setR2(o.getString("r2", getR2()));
        setR3(o.getString("r3", getR3()));
        setR4(o.getString("r4", getR4()));
        setAddress1(o.getString("address1", getAddress1()));
        setAddress2(o.getString("address2", getAddress2()));
        setPostalCode(o.getString("postalCode", getPostalCode()));
    }

    @Override
    default void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

}
