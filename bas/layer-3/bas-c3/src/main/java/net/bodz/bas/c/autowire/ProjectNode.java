package net.bodz.bas.c.autowire;

import java.util.ArrayList;
import java.util.List;

public class ProjectNode {

    public ProjectMetadata project;
    public List<ProjectNode> dependencies = new ArrayList<>();

}
