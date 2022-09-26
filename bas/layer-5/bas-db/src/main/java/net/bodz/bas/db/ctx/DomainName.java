package net.bodz.bas.db.ctx;

public class DomainName {

    public static String reverse(String name) {
        StringBuilder sb = new StringBuilder(name.length());
        int count = 0;
        while (name != null) {
            int pos = name.lastIndexOf('.');
            String token;
            if (pos == -1) {
                token = name;
                name = null;
            } else {
                token = name.substring(pos + 1);
                name = name.substring(0, pos);
            }
            if (count++ != 0)
                sb.append('.');
            sb.append(token);
        }
        return sb.toString();
    }

}
