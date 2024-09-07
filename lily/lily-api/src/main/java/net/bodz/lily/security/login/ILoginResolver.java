package net.bodz.lily.security.login;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.site.json.AbstractJsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.IUser;

@IndexedType
public interface ILoginResolver {

    /**
     * @return {@link Result} contains the user or error message if any.
     *
     *         Returns <code>null</code> to try other resolver.
     */
    Result login(ISignatureChecker checker, IVariantMap<String> q);

    public class Result
            extends AbstractJsonResponse<Result> {

        // Integer userId;
        IUser user;

        public Result() {
        }

        public Result(IUser user) {
            this(user, OK, null);
        }

        public Result(IUser user, int statusCode, String message) {
            super(statusCode, message, null);
            this.user = user;
        }

        public IUser getUser() {
            return user;
        }

        public void setUser(IUser user) {
            this.user = user;
        }

        @Override
        public void jsonOut(IJsonOut out, JsonFormOptions opts)
                throws IOException, FormatException {
            super.jsonOut(out, opts);
            out.entry("user", user);
        }

        public LoginResult toLoginResult(ILoginManager manager) {
            LoginResult result = new LoginResult(this, true);
            if (isSuccess())
                result.token = manager.newToken(user);
            return result;
        }

        @Deprecated
        public static LoginResult toLoginResult(Result r, ILoginManager manager) {
            if (r == null)
                return new LoginResult().fail("not available.");
            else
                return r.toLoginResult(manager);
        }

    }

}
