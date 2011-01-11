package net.bodz.bas.sysutil;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class DesktopApps {

    static boolean _encode = false;

    public static void openMailer(String recipient, String subject, String body) throws IOException {
        StringBuffer buf = new StringBuffer();
        buf.append("mailto:"); 
        buf.append(recipient);
        buf.append("?subject=" + _encode(subject)); 
        buf.append("&body=" + _encode(body)); 
        String s = buf.toString();
        URI mailURI;
        try {
            mailURI = new URI(s);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Desktop desktop = Desktop.getDesktop();
        desktop.mail(mailURI);
    }

    static String _encode(String s) {
        try {
            s = URLEncoder.encode(s, "utf-8"); 
            return s.replace("+", "%20");  
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
