package net.bodz.dist.ins;

import java.net.URL;

public interface IProject extends IComponent {

    URL getIconURL();

    String getVersion();

    String getUpdateTime();

    String getCompany();

    String getLicense();

    Scheme[] getSchemes();

    BaseDir[] getBaseDirs();

}
