package net.bodz.bas.c.autowire;

import java.util.ArrayList;
import java.util.List;

public class ProjectNode {

    public String name;
    public List<ProjectNode> dependencies = new ArrayList<>();

}
