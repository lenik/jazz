package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.AbstractDataService;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.sms.IShortMessageService;
import net.bodz.bas.sms.SmsCommitLog;
import net.bodz.bas.sms.SmsProviders;
import net.bodz.lily.io.ISmsService;
import net.bodz.lily.security.auth.ExtServiceException;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.auth.IAuthModule;

public class DefaultSmsService
        extends AbstractDataService
        implements
            ISmsService {

    static final Logger logger = LoggerFactory.getLogger(DefaultSmsService.class);

    private static final String K_LOGS = "logs";

    public DefaultSmsService(DataContext dataContext) {
        super(dataContext);
    }

    /**
     * @param usage
     *            The reason to verify.
     * @see ILoginManager#VERIFY
     */
    @Override
    public JsonResult sendVerificationCodeToPhone(String phone, String usage)
            throws ExtServiceException {
        JsonResult resp = new JsonResult();
        String shortCode = FlyingCodes.smsAuth.sign(phone).snapshot();
        if (FlyingCodes.debug)
            resp.setHeader(IAuthModule.K_ENC_CLIENT_RESP, shortCode);

        IShortMessageService sms = SmsProviders.getSms();
        SmsCommitLog log = new SmsCommitLog();
        try {
            sms.addSmsListener(log);
            // if (!debug)
            if (! sms.sendPrepared(phone, usage, shortCode, FlyingCodes.smsTimeout)) {
                throw new ExtServiceException("sms isn't available.");
            }
        } catch (Exception e) {
            throw new ExtServiceException("send sms error: " + e.getMessage(), e);
        } finally {
            sms.removeSmsListener(log);
            resp.setHeader(K_LOGS, JsonFn.union(log));
        }
        return resp;
    }

}
