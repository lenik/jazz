package net.bodz.dist.ins;

import java.net.URL;
import java.util.Collection;

public interface IProject {

    String getName();

    String getCaption();

    String getDescription();

    URL getIconURL();

    String getVersion();

    String getUpdateTime();

    String getCompany();

    String getLicense();

    Scheme[] getSchemes();

    Collection<IComponent> getRootComponents();

}
