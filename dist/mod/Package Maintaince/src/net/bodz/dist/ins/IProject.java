package net.bodz.dist.ins;

import org.eclipse.swt.graphics.ImageData;

public interface IProject extends IComponent {

    ImageData getLogo();

    String getVersion();

    String getCompany();

    String getLicense();

    String getUpdateTime();

    Scheme[] getSchemes();

    BaseDir[] getBaseDirs();

}
