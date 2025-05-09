package net.bodz.bas.vfs.path;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.vfs.path.align.IPathAlignment;

public interface IMutablePath
        extends IPath {

    void setProtocol(String protocol);

    void setDeviceSpec(String deviceSpec);

    void setAlignment(IPathAlignment alignment);

    void setLocalPath(@NotNull String localPath);

    void setDirName(@NotNull String dirName);

    void setBaseName(@NotNull String baseName);

    void setName(@NotNull String name);

    void setExtension(String extension);

    void setDotExtension(String dotExtension);

    void setExtension(int maxWords, String extension);

    void setDotExtension(int maxWords, String dotExtension);

    void setEntered();

    void setEscaped();

}
