package net.bodz.dist.ins;

import java.io.File;

public class BaseDir {

    private String name;
    private String displayName;
    private String description;
    private File   preferred;

    public BaseDir() {
    }

    public BaseDir(String name, String displayName, String description,
            File preferred) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.preferred = preferred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getPreferred() {
        return preferred;
    }

    public void setPreferred(File preferred) {
        this.preferred = preferred;
    }

}
