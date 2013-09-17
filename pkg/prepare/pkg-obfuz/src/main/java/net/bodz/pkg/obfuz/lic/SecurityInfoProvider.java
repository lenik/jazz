package net.bodz.pkg.obfuz.lic;

public interface SecurityInfoProvider {

    PrepareLoginInfo prepareLogin();

    Session login(LoginInfo loginInfo);

}
