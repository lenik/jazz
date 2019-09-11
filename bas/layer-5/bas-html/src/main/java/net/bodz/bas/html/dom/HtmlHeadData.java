package net.bodz.bas.html.dom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.html.artifact.MutableArtifactDependent;
import net.bodz.bas.html.dom.tag.MutableHead;
import net.bodz.bas.html.dom.tag.MutableLink;
import net.bodz.bas.html.dom.tag.MutableScript;
import net.bodz.bas.html.dom.tag.MutableStyle;

public class HtmlHeadData
        extends MutableArtifactDependent
        implements IHtmlHeadData {

    private String title;
    private Map<String, String> metaMap;
    private Map<String, String> httpEquivMetaMap;

    private MutableHead optHead;
    private List<MutableLink> links;
    private List<MutableScript> scripts;
    private List<MutableStyle> styles;

    public HtmlHeadData() {
        metaMap = new TreeMap<String, String>();
        httpEquivMetaMap = new TreeMap<String, String>();

        optHead = new MutableHead(null);
        links = new ArrayList<MutableLink>();
        scripts = new ArrayList<MutableScript>();
        styles = new ArrayList<MutableStyle>();
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

    @Override
    public MutableHead getOptHead() {
        return optHead;
    }

    @Override
    public List<MutableLink> getLinks() {
        return links;
    }

    @Override
    public void addLink(MutableLink link) {
        links.add(link);
    }

    @Override
    public List<MutableScript> getScripts() {
        return scripts;
    }

    @Override
    public void addScript(MutableScript script) {
        scripts.add(script);
    }

    @Override
    public List<MutableStyle> getStyles() {
        return styles;
    }

    @Override
    public void addStyle(MutableStyle style) {
        styles.add(style);
    }

}
