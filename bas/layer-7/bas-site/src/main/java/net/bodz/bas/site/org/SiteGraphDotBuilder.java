package net.bodz.bas.site.org;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.c.type.ClassResource;
import net.bodz.bas.fmt.textmap.TextMapParser;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.t.set.IMarkset;
import net.bodz.bas.t.set.Marks;

public class SiteGraphDotBuilder
        extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    private ITreeOut out;
    private UnixStyleVarExpander expander = new UnixStyleVarExpander(this);

    public SiteGraphDotBuilder(ITreeOut out) {
        this.out = out;
        put("layout", "circo");
        put("mindist", "0");
        put("fontname", "Sans Serif");
        put("fontsize", 20);
    }

    public void buildGraph(SiteGraphNode root) {
        put("title", root.getLabel());

        out.println("digraph site {");
        String graphAttrs = expander.process(graphAttrsTemplate);
        out.println(graphAttrs);
        out.enter();

        Marks markSet = new Marks();
        buildNodes(markSet, root);

        out.println();
        markSet.clearMarks();
        buildEdges(markSet, root);

        out.leave();
        out.println("}");
    }

    void buildNodes(IMarkset marks, SiteGraphNode node) {
        if (!marks.addMark(node))
            return;

        StringBuilder sb = new StringBuilder();
        sb.append(node.getId());

        Map<String, Object> attrs = new HashMap<String, Object>();
        String label = node.getName(); // getLabel().toString();
        attrs.put("label", StringQuote.qqJavaString(label));
        attrs.put("fontsize", 30 - node.getLevel() * 5);
        attrs.put("shape", node.isLeaf() ? "note" : "box3d");
        attrs.put("href", StringQuote.qq(node.getHref()));

        sb.append(" [");
        int i = 0;
        for (Entry<String, ?> entry : attrs.entrySet()) {
            if (i++ != 0)
                sb.append(", ");
            sb.append(entry.getKey());
            sb.append(" = ");
            sb.append(entry.getValue());
        }

        sb.append("];");
        out.println(sb);

        for (SiteGraphRelation relation : node.getRelations())
            buildNodes(marks, relation.getDst());
    }

    void buildEdges(IMarkset marks, SiteGraphNode node) {
        if (!marks.addMark(node))
            return;

        String srcId = node.getId();
        for (SiteGraphRelation relation : node.getRelations()) {
            SiteGraphNode dst = relation.getDst();
            String dstId = dst.getId();

            StringBuilder sb = new StringBuilder();
            sb.append(srcId);
            sb.append(" -> ");
            sb.append(dstId);

            Map<String, String> attrs = new HashMap<String, String>();

            sb.append(" [");
            int i = 0;
            for (Entry<String, String> entry : attrs.entrySet()) {
                if (i++ != 0)
                    sb.append(", ");
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(entry.getValue());
            }

            sb.append("];");

            out.println(sb);
        }

        for (SiteGraphRelation relation : node.getRelations())
            buildEdges(marks, relation.getDst());
    }

    static String graphAttrsTemplate;

    static {
        reloadTemplates();
    }

    static void reloadTemplates() {
        Map<String, String> templates = new HashMap<String, String>();

        URL url = ClassResource.getDataURL(SiteGraphDotBuilder.class, "tm");
        for (Entry<String, String> ent : TextMapParser.parse(ResFn.url(url))) {
            templates.put(ent.getKey(), ent.getValue());
        }
        graphAttrsTemplate = templates.get("graph.attrs");
    }

}
