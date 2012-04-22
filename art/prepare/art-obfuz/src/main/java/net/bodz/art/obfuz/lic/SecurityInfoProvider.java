package net.bodz.art.obfuz.lic;

public interface SecurityInfoProvider {

    PrepareLoginInfo prepareLogin();

    Session login(LoginInfo loginInfo);

}
