package net.bodz.redist.obfuz.lic;

public interface SecurityInfoProvider {

    PrepareLoginInfo prepareLogin();

    Session login(LoginInfo loginInfo);

}
