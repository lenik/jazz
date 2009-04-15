package net.bodz.dist.ins;

import java.util.Map;

import org.eclipse.swt.graphics.ImageData;

public interface IProject extends IComponent {

    /**
     * This should be a bigger logo image compared to {@link #getImage()}.
     */
    ImageData getLogo();

    String getVersion();

    String getCompany();

    String getLicense();

    String getUpdateTime();

    /**
     * The schemes, only used by installer.
     */
    Scheme[] getSchemes();

    /**
     * The base directories and their default values, they are cloned for each
     * session.
     */
    BaseDir[] getBaseDirs();

    /**
     * The initial environment, the returned {@link Map} will be cloned for each
     * session.
     */
    Map<String, Object> getEnv();

}
