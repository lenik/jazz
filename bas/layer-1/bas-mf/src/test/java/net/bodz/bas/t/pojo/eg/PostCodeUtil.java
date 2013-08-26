package net.bodz.bas.t.pojo.eg;

public class PostCodeUtil {

    public String getCityFromCode(int code) {
        if (code == 310000)
            return "Zhejiang";
        throw new IllegalArgumentException("Invalid post code: " + code);
    }

}
