package net.bodz.lily.io;

import net.bodz.bas.site.json.JsonResult;
import net.bodz.lily.security.auth.ExtServiceException;

public interface IEmailService {

    JsonResult sendVerificationCodeToEmail(String emailAddress)
            throws ExtServiceException;

}
