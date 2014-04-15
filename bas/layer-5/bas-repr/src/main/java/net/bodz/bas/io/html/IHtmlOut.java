package net.bodz.bas.io.html;

import net.bodz.bas.io.html.tag.*;
import net.bodz.bas.io.xml.IXmlOut;

public interface IHtmlOut
        extends IXmlOut {

    HtmlABuilder a();

    HtmlAbbrBuilder abbr();

    HtmlAddressBuilder address();

    HtmlAreaBuilder area();

    HtmlArticleBuilder article();

    HtmlAsideBuilder aside();

    HtmlAudioBuilder audio();

    HtmlBBuilder b();

    HtmlBaseBuilder base();

    HtmlBdiBuilder bdi();

    HtmlBdoBuilder bdo();

    HtmlBlockquoteBuilder blockquote();

    HtmlBodyBuilder body();

    HtmlBrBuilder br();

    HtmlButtonBuilder button();

    HtmlCanvasBuilder canvas();

    HtmlCaptionBuilder caption();

    HtmlCiteBuilder cite();

    HtmlCodeBuilder code();

    HtmlColBuilder col();

    HtmlColgroupBuilder colgroup();

    HtmlDataBuilder data();

    HtmlDatalistBuilder datalist();

    HtmlDdBuilder dd();

    HtmlDelBuilder del();

    HtmlDetailsBuilder details();

    HtmlDfnBuilder dfn();

    HtmlDialogBuilder dialog();

    HtmlDivBuilder div();

    HtmlDlBuilder dl();

    HtmlDtBuilder dt();

    HtmlEmBuilder em();

    HtmlEmbedBuilder embed();

    HtmlFieldsetBuilder fieldset();

    HtmlFigcaptionBuilder figcaption();

    HtmlFigureBuilder figure();

    HtmlFooterBuilder footer();

    HtmlFormBuilder form();

    HtmlH1Builder h1();

    HtmlH2Builder h2();

    HtmlH3Builder h3();

    HtmlH4Builder h4();

    HtmlH5Builder h5();

    HtmlH6Builder h6();

    HtmlHeadBuilder head();

    HtmlHeaderBuilder header();

    HtmlHrBuilder hr();

    HtmlHtmlBuilder html();

    HtmlIBuilder i();

    HtmlIframeBuilder iframe();

    HtmlImgBuilder img();

    HtmlInputBuilder input();

    HtmlInsBuilder ins();

    HtmlKbdBuilder kbd();

    HtmlKeygenBuilder keygen();

    HtmlLabelBuilder label();

    HtmlLegendBuilder legend();

    HtmlLiBuilder li();

    HtmlLinkBuilder link();

    HtmlMainBuilder main();

    HtmlMapBuilder map();

    HtmlMarkBuilder mark();

    HtmlMetaBuilder meta();

    HtmlMeterBuilder meter();

    HtmlNavBuilder nav();

    HtmlNoscriptBuilder noscript();

    HtmlObjectBuilder object();

    HtmlOlBuilder ol();

    HtmlOptgroupBuilder optgroup();

    HtmlOptionBuilder option();

    HtmlOutputBuilder output();

    HtmlPBuilder p();

    HtmlParamBuilder param();

    HtmlPreBuilder pre();

    HtmlProgressBuilder progress();

    HtmlQBuilder q();

    HtmlRbBuilder rb();

    HtmlRpBuilder rp();

    HtmlRtBuilder rt();

    HtmlRtcBuilder rtc();

    HtmlRubyBuilder ruby();

    HtmlSBuilder s();

    HtmlSampBuilder samp();

    HtmlScriptBuilder script();

    HtmlSectionBuilder section();

    HtmlSelectBuilder select();

    HtmlSmallBuilder small();

    HtmlSourceBuilder source();

    HtmlSpanBuilder span();

    HtmlStrongBuilder strong();

    HtmlStyleBuilder style();

    HtmlSubandsupBuilder subandsup();

    HtmlSummaryBuilder summary();

    HtmlTableBuilder table();

    HtmlTbodyBuilder tbody();

    HtmlTdBuilder td();

    HtmlTemplateBuilder template();

    HtmlTextareaBuilder textarea();

    HtmlTfootBuilder tfoot();

    HtmlThBuilder th();

    HtmlTheadBuilder thead();

    HtmlTimeBuilder time();

    HtmlTitleBuilder title();

    HtmlTrBuilder tr();

    HtmlTrackBuilder track();

    HtmlUBuilder u();

    HtmlUlBuilder ul();

    HtmlVarBuilder var();

    HtmlVideoBuilder video();

    HtmlWbrBuilder wbr();

    IHtmlOut NULL = new NullHtmlOut();

}
