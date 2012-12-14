package user;

import java.net.URL;

import net.bodz.bas.c.loader.ClassResource;

public class JarURLPath {

    public static void main(String[] args) {
        URL url = ClassResource.getClassBytesURL(String.class);
        System.out.println(url);
        System.out.println(url.getFile());
        System.out.println(url.getUserInfo());
        System.out.println(url.getAuthority());
    }

}
