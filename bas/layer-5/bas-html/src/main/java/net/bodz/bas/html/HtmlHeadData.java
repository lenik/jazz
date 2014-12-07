package net.bodz.bas.html;

import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.html.artifact.MutableArtifactDependent;

public class HtmlHeadData
        extends MutableArtifactDependent
        implements IHtmlHeadData {

    private String title;
    private Map<String, String> metaMap;
    private Map<String, String> httpEquivMetaMap;

    public HtmlHeadData() {
        metaMap = new TreeMap<>();
        httpEquivMetaMap = new TreeMap<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Map<String, String> getHttpEquivMetaMap() {
        return httpEquivMetaMap;
    }

    @Override
    public String getHttpEquivMeta(String httpEquiv) {
        return httpEquivMetaMap.get(httpEquiv);
    }

    @Override
    public void setHttpEquivMeta(String httpEquiv, String content) {
        httpEquivMetaMap.put(httpEquiv, content);
    }

    @Override
    public Map<String, String> getMetaMap() {
        return metaMap;
    }

    @Override
    public String getMeta(String name) {
        return metaMap.get(name);
    }

    @Override
    public void setMeta(String name, String content) {
        metaMap.put(name, content);
    }

}
