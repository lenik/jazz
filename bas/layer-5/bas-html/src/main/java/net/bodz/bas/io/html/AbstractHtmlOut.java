package net.bodz.bas.io.html;

import net.bodz.bas.io.html.tag.*;
import net.bodz.bas.io.xml.AbstractXmlOut;
import net.bodz.bas.io.xml.XmlOutputFormat;

public abstract class AbstractHtmlOut
        extends AbstractXmlOut
        implements IHtmlOut {

    public AbstractHtmlOut() {
        super();
    }

    public AbstractHtmlOut(XmlOutputFormat outputFormat) {
        super(outputFormat);
    }

    /** ⇱ Implementation Of {@link IHtmlOut}. */
    /* _____________________________ */static section.iface __HTML__;

    public HtmlABuilder a() {
        return new HtmlABuilder(tag("a"));
    }

    public HtmlAbbrBuilder abbr() {
        return new HtmlAbbrBuilder(tag("abbr"));
    }

    public HtmlAddressBuilder address() {
        return new HtmlAddressBuilder(tag("address"));
    }

    public HtmlAreaBuilder area() {
        return new HtmlAreaBuilder(tag("area"));
    }

    public HtmlArticleBuilder article() {
        return new HtmlArticleBuilder(tag("article"));
    }

    public HtmlAsideBuilder aside() {
        return new HtmlAsideBuilder(tag("aside"));
    }

    public HtmlAudioBuilder audio() {
        return new HtmlAudioBuilder(tag("audio"));
    }

    public HtmlBBuilder b() {
        return new HtmlBBuilder(tag("b"));
    }

    public HtmlBaseBuilder base() {
        return new HtmlBaseBuilder(tag("base"));
    }

    public HtmlBdiBuilder bdi() {
        return new HtmlBdiBuilder(tag("bdi"));
    }

    public HtmlBdoBuilder bdo() {
        return new HtmlBdoBuilder(tag("bdo"));
    }

    public HtmlBlockquoteBuilder blockquote() {
        return new HtmlBlockquoteBuilder(tag("blockquote"));
    }

    public HtmlBodyBuilder body() {
        return new HtmlBodyBuilder(tag("body"));
    }

    public HtmlBrBuilder br() {
        return new HtmlBrBuilder(tag("br"));
    }

    public HtmlButtonBuilder button() {
        return new HtmlButtonBuilder(tag("button"));
    }

    public HtmlCanvasBuilder canvas() {
        return new HtmlCanvasBuilder(tag("canvas"));
    }

    public HtmlCaptionBuilder caption() {
        return new HtmlCaptionBuilder(tag("caption"));
    }

    public HtmlCiteBuilder cite() {
        return new HtmlCiteBuilder(tag("cite"));
    }

    public HtmlCodeBuilder code() {
        return new HtmlCodeBuilder(tag("code"));
    }

    public HtmlColBuilder col() {
        return new HtmlColBuilder(tag("col"));
    }

    public HtmlColgroupBuilder colgroup() {
        return new HtmlColgroupBuilder(tag("colgroup"));
    }

    public HtmlDataBuilder data() {
        return new HtmlDataBuilder(tag("data"));
    }

    public HtmlDatalistBuilder datalist() {
        return new HtmlDatalistBuilder(tag("datalist"));
    }

    public HtmlDdBuilder dd() {
        return new HtmlDdBuilder(tag("dd"));
    }

    public HtmlDelBuilder del() {
        return new HtmlDelBuilder(tag("del"));
    }

    public HtmlDetailsBuilder details() {
        return new HtmlDetailsBuilder(tag("details"));
    }

    public HtmlDfnBuilder dfn() {
        return new HtmlDfnBuilder(tag("dfn"));
    }

    public HtmlDialogBuilder dialog() {
        return new HtmlDialogBuilder(tag("dialog"));
    }

    public HtmlDivBuilder div() {
        return new HtmlDivBuilder(tag("div"));
    }

    public HtmlDlBuilder dl() {
        return new HtmlDlBuilder(tag("dl"));
    }

    public HtmlDtBuilder dt() {
        return new HtmlDtBuilder(tag("dt"));
    }

    public HtmlEmBuilder em() {
        return new HtmlEmBuilder(tag("em"));
    }

    public HtmlEmbedBuilder embed() {
        return new HtmlEmbedBuilder(tag("embed"));
    }

    public HtmlFieldsetBuilder fieldset() {
        return new HtmlFieldsetBuilder(tag("fieldset"));
    }

    public HtmlFigcaptionBuilder figcaption() {
        return new HtmlFigcaptionBuilder(tag("figcaption"));
    }

    public HtmlFigureBuilder figure() {
        return new HtmlFigureBuilder(tag("figure"));
    }

    public HtmlFooterBuilder footer() {
        return new HtmlFooterBuilder(tag("footer"));
    }

    public HtmlFormBuilder form() {
        return new HtmlFormBuilder(tag("form"));
    }

    public HtmlH1Builder h1() {
        return new HtmlH1Builder(tag("h1"));
    }

    public HtmlH2Builder h2() {
        return new HtmlH2Builder(tag("h2"));
    }

    public HtmlH3Builder h3() {
        return new HtmlH3Builder(tag("h3"));
    }

    public HtmlH4Builder h4() {
        return new HtmlH4Builder(tag("h4"));
    }

    public HtmlH5Builder h5() {
        return new HtmlH5Builder(tag("h5"));
    }

    public HtmlH6Builder h6() {
        return new HtmlH6Builder(tag("h6"));
    }

    public HtmlHeadBuilder head() {
        return new HtmlHeadBuilder(tag("head"));
    }

    public HtmlHeaderBuilder header() {
        return new HtmlHeaderBuilder(tag("header"));
    }

    public HtmlHrBuilder hr() {
        return new HtmlHrBuilder(tag("hr"));
    }

    public HtmlHtmlBuilder html() {
        return new HtmlHtmlBuilder(tag("html"));
    }

    public HtmlIBuilder i() {
        return new HtmlIBuilder(tag("i"));
    }

    public HtmlIframeBuilder iframe() {
        return new HtmlIframeBuilder(tag("iframe"));
    }

    public HtmlImgBuilder img() {
        return new HtmlImgBuilder(tag("img"));
    }

    public HtmlInputBuilder input() {
        return new HtmlInputBuilder(tag("input"));
    }

    public HtmlInsBuilder ins() {
        return new HtmlInsBuilder(tag("ins"));
    }

    public HtmlKbdBuilder kbd() {
        return new HtmlKbdBuilder(tag("kbd"));
    }

    public HtmlKeygenBuilder keygen() {
        return new HtmlKeygenBuilder(tag("keygen"));
    }

    public HtmlLabelBuilder label() {
        return new HtmlLabelBuilder(tag("label"));
    }

    public HtmlLegendBuilder legend() {
        return new HtmlLegendBuilder(tag("legend"));
    }

    public HtmlLiBuilder li() {
        return new HtmlLiBuilder(tag("li"));
    }

    public HtmlLinkBuilder link() {
        return new HtmlLinkBuilder(tag("link"));
    }

    public HtmlMainBuilder main() {
        return new HtmlMainBuilder(tag("main"));
    }

    public HtmlMapBuilder map() {
        return new HtmlMapBuilder(tag("map"));
    }

    public HtmlMarkBuilder mark() {
        return new HtmlMarkBuilder(tag("mark"));
    }

    public HtmlMetaBuilder meta() {
        return new HtmlMetaBuilder(tag("meta"));
    }

    public HtmlMeterBuilder meter() {
        return new HtmlMeterBuilder(tag("meter"));
    }

    public HtmlNavBuilder nav() {
        return new HtmlNavBuilder(tag("nav"));
    }

    public HtmlNoscriptBuilder noscript() {
        return new HtmlNoscriptBuilder(tag("noscript"));
    }

    public HtmlObjectBuilder object() {
        return new HtmlObjectBuilder(tag("object"));
    }

    public HtmlOlBuilder ol() {
        return new HtmlOlBuilder(tag("ol"));
    }

    public HtmlOptgroupBuilder optgroup() {
        return new HtmlOptgroupBuilder(tag("optgroup"));
    }

    public HtmlOptionBuilder option() {
        return new HtmlOptionBuilder(tag("option"));
    }

    public HtmlOutputBuilder output() {
        return new HtmlOutputBuilder(tag("output"));
    }

    public HtmlPBuilder p() {
        return new HtmlPBuilder(tag("p"));
    }

    public HtmlParamBuilder param() {
        return new HtmlParamBuilder(tag("param"));
    }

    public HtmlPreBuilder pre() {
        return new HtmlPreBuilder(tag("pre"));
    }

    public HtmlProgressBuilder progress() {
        return new HtmlProgressBuilder(tag("progress"));
    }

    public HtmlQBuilder q() {
        return new HtmlQBuilder(tag("q"));
    }

    public HtmlRbBuilder rb() {
        return new HtmlRbBuilder(tag("rb"));
    }

    public HtmlRpBuilder rp() {
        return new HtmlRpBuilder(tag("rp"));
    }

    public HtmlRtBuilder rt() {
        return new HtmlRtBuilder(tag("rt"));
    }

    public HtmlRtcBuilder rtc() {
        return new HtmlRtcBuilder(tag("rtc"));
    }

    public HtmlRubyBuilder ruby() {
        return new HtmlRubyBuilder(tag("ruby"));
    }

    public HtmlSBuilder s() {
        return new HtmlSBuilder(tag("s"));
    }

    public HtmlSampBuilder samp() {
        return new HtmlSampBuilder(tag("samp"));
    }

    public HtmlScriptBuilder script() {
        return new HtmlScriptBuilder(tag("script"));
    }

    public HtmlSectionBuilder section() {
        return new HtmlSectionBuilder(tag("section"));
    }

    public HtmlSelectBuilder select() {
        return new HtmlSelectBuilder(tag("select"));
    }

    public HtmlSmallBuilder small() {
        return new HtmlSmallBuilder(tag("small"));
    }

    public HtmlSourceBuilder source() {
        return new HtmlSourceBuilder(tag("source"));
    }

    public HtmlSpanBuilder span() {
        return new HtmlSpanBuilder(tag("span"));
    }

    public HtmlStrongBuilder strong() {
        return new HtmlStrongBuilder(tag("strong"));
    }

    public HtmlStyleBuilder style() {
        return new HtmlStyleBuilder(tag("style"));
    }

    public HtmlSubandsupBuilder subandsup() {
        return new HtmlSubandsupBuilder(tag("subandsup"));
    }

    public HtmlSummaryBuilder summary() {
        return new HtmlSummaryBuilder(tag("summary"));
    }

    public HtmlTableBuilder table() {
        return new HtmlTableBuilder(tag("table"));
    }

    public HtmlTbodyBuilder tbody() {
        return new HtmlTbodyBuilder(tag("tbody"));
    }

    public HtmlTdBuilder td() {
        return new HtmlTdBuilder(tag("td"));
    }

    public HtmlTemplateBuilder template() {
        return new HtmlTemplateBuilder(tag("template"));
    }

    public HtmlTextareaBuilder textarea() {
        return new HtmlTextareaBuilder(tag("textarea"));
    }

    public HtmlTfootBuilder tfoot() {
        return new HtmlTfootBuilder(tag("tfoot"));
    }

    public HtmlThBuilder th() {
        return new HtmlThBuilder(tag("th"));
    }

    public HtmlTheadBuilder thead() {
        return new HtmlTheadBuilder(tag("thead"));
    }

    public HtmlTimeBuilder time() {
        return new HtmlTimeBuilder(tag("time"));
    }

    public HtmlTitleBuilder title() {
        return new HtmlTitleBuilder(tag("title"));
    }

    public HtmlTrBuilder tr() {
        return new HtmlTrBuilder(tag("tr"));
    }

    public HtmlTrackBuilder track() {
        return new HtmlTrackBuilder(tag("track"));
    }

    public HtmlUBuilder u() {
        return new HtmlUBuilder(tag("u"));
    }

    public HtmlUlBuilder ul() {
        return new HtmlUlBuilder(tag("ul"));
    }

    public HtmlVarBuilder var() {
        return new HtmlVarBuilder(tag("var"));
    }

    public HtmlVideoBuilder video() {
        return new HtmlVideoBuilder(tag("video"));
    }

    public HtmlWbrBuilder wbr() {
        return new HtmlWbrBuilder(tag("wbr"));
    }

}
