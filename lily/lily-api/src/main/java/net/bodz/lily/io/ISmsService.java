package net.bodz.lily.io;

import net.bodz.bas.site.json.JsonResult;
import net.bodz.lily.security.auth.ExtServiceException;

public interface ISmsService {

    JsonResult sendVerificationCodeToPhone(String phoneNumber, String usageText)
            throws ExtServiceException;

}
