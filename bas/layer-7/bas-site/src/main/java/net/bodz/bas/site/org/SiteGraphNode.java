package net.bodz.bas.site.org;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.repr.content.IContent;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.AbstractXjdocElement;
import net.bodz.mda.xjdoc.model.javadoc.IXjdocAware;

public class SiteGraphNode
        extends AbstractXjdocElement
        implements ICrawler {

    private String id;
    private String name;
    private String href;

    private SiteGraphNode parent;
    private boolean leaf = true;

    private List<SiteGraphRelation> relations = new ArrayList<SiteGraphRelation>();

    private int idSeq = 1;

    public SiteGraphNode() {
        this.id = "root";
    }

    public SiteGraphNode(SiteGraphNode parent) {
        SiteGraphNode root = parent.getRoot();
        int next = root.idSeq++;
        this.id = "node" + next;

        this.parent = parent;
        parent.setLeaf(false);
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public SiteGraphNode getParent() {
        return parent;
    }

    public SiteGraphNode getRoot() {
        return parent == null ? this : parent.getRoot();
    }

    public int getLevel() {
        int level = 0;
        SiteGraphNode node = parent;
        while (node != null) {
            level++;
            node = node.getParent();
        }
        return level;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public List<SiteGraphRelation> getRelations() {
        return relations;
    }

    @Override
    public void follow(String subpath, IContent content) {
        SiteGraphNode node = new SiteGraphNode(this);

        if (content instanceof IElement) {
            IElement element = ((IElement) content);
            node.setName(element.getName());
        }

        StringBuilder hrefBuf = new StringBuilder();
        hrefBuf.append(href);
        if (!href.endsWith("/"))
            hrefBuf.append('/');
        hrefBuf.append(subpath);
        node.setHref(hrefBuf.toString());

        if (content instanceof IXjdocAware) {
            IElementDoc xjdoc = ((IXjdocAware) content).getXjdoc();
            node.setXjdoc(xjdoc);
        }

        SiteGraphRelation relation = new SiteGraphRelation(subpath, this, node);
        relations.add(relation);

        if (content instanceof ICrawlable) {
            ICrawlable composite = (ICrawlable) content;
            composite.crawlableIntrospect(node);
        }
    }

}
