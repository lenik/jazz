package net.bodz.bas.std.rfc.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ContentDisposition {

    public static String format(String filename, boolean attachment, boolean inline) {
        String contentDisposition = null;

        if (attachment)
            contentDisposition = "attachment";
        else if (inline)
            contentDisposition = "inline";

        if (filename != null) {
            /**
             * @see RFC 5987 2.3
             */
            String encodedFilename;
            try {
                encodedFilename = URLEncoder.encode(filename, "utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            // This is for IE8:
            // contentDisposition += "; filename=" + encodedFilename;

            // This is for Firefox and rest.
            contentDisposition += "; filename*=UTF-8''" + encodedFilename;
        }

        return contentDisposition;
    }

}
