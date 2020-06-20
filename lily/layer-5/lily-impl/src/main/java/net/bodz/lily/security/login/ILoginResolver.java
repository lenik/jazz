package net.bodz.lily.security.login;

import java.io.IOException;

import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.site.json.AbstractJsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.login.key.ISignatureChecker;

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
        User user;

        public Result() {
        }

        public Result(User user) {
            this(user, OK, null);
        }

        public Result(User user, int statusCode, String message) {
            super(statusCode, message, null);
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public void writeObject(IJsonOut out)
                throws IOException {
            super.writeObject(out);
            out.entry("user", user);
        }

        public LoginResult toLoginResult(LoginManager manager) {
            LoginResult result = new LoginResult(this, true);
            if (isSuccess())
                result.token = manager.newToken(user);
            return result;
        }

        @Deprecated
        public static LoginResult toLoginResult(Result r, LoginManager manager) {
            if (r == null)
                return new LoginResult().fail("not available.");
            else
                return r.toLoginResult(manager);
        }

    }

}
