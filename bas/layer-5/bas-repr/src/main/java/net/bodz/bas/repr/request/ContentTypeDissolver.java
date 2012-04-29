package net.bodz.bas.repr.request;

import net.bodz.bas.repr.rest.RESTfulRequest;
import net.bodz.bas.vfs.util.MIMEType;

public class ContentTypeDissolver
        implements ISuffixDissolver {

    @Override
    public boolean dissolveSuffix(String extension, RESTfulRequest model) {
        if (extension == null)
            throw new NullPointerException("extension");

        MIMEType contentType = MIMEType.getInstanceByExtension(extension);
        if (contentType == null)
            return false;

        model.setTargetContentType(contentType);
        return true;
    }

    public static final ContentTypeDissolver INSTANCE = new ContentTypeDissolver();

}
