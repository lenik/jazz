package net.bodz.bas.site.artifact;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.html.artifact.Group;
import net.bodz.bas.html.artifact.IArtifact;

public class ZwkPackage
        extends Group {

    private static final long serialVersionUID = 1L;

    Map<String, IArtifact> pageMap = new HashMap<String, IArtifact>();

    public ZwkPackage(String name, String versionStr) {
        super(name, versionStr);
    }

    public ZwkPackage(String name) {
        super(name);
    }

    static Pattern lastNumber = Pattern.compile("(\\d+)$");

    @Override
    public void addChild(IArtifact artifact) {
        super.addChild(artifact);

        String name = artifact.getName();
        Matcher matcher = lastNumber.matcher(name);
        if (!matcher.find())
            throw new IllegalArgumentException("Zwk artifact without page number.");

        String pageStr = matcher.group(1);
        pageMap.put(pageStr, artifact);
    }

    public IArtifact page(Object page) {
        if (page == null)
            throw new NullPointerException("page");
        String pageStr = page.toString();
        IArtifact artifact = pageMap.get(pageStr);
        if (artifact == null)
            throw new IllegalArgumentException(String.format(//
                    "Undefined page %s in %s.", pageStr, getName()));
        return artifact;
    }

}
