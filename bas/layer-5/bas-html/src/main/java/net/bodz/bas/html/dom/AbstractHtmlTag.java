package net.bodz.bas.html.dom;

import net.bodz.bas.html.dom.tag.*;
import net.bodz.bas.ui.css3.property.DirectionMode;
import net.bodz.bas.xml.dom.AbstractXmlTag;
import net.bodz.bas.xml.dom.IXmlTag;

public abstract class AbstractHtmlTag<self_t extends IHtmlTag>
        extends AbstractXmlTag<self_t>
        implements IHtmlTag {

    protected AbstractHtmlTag(IXmlTag parent) {
        super(parent);
    }

    public AbstractHtmlTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    @Override
    public IHtmlTag insert(String tagName) {
        return new HtmlTag(this, tagName);
    }

    @Override
    public self_t id(String id) {
        HtmlDoc doc = (HtmlDoc) getRoot();
        if (doc != null) {
            doc.getTagMap().add(id, this);
        }
        return attr("id", id);
    }

    @Override
    public self_t accesskey(String accesskey) {
        return attr("accesskey", accesskey);
    }

    @Override
    public self_t class_(String class_) {
        return attr("class", class_);
    }

    @Override
    public self_t contenteditable(Boolean contenteditable) {
        return attr("contenteditable", contenteditable);
    }

    @Override
    public self_t dir(DirectionMode dir) {
        return attr("dir", dir);
    }

    @Override
    public self_t draggable(Boolean draggable) {
        return attr("draggable", draggable);
    }

    @Override
    public self_t dropzone(String dropzone) {
        return attr("dropzone", dropzone);
    }

    @Override
    public self_t hidden(Boolean hidden) {
        return attr("hidden", hidden);
    }

    @Override
    public self_t lang(String lang) {
        return attr("lang", lang);
    }

    @Override
    public self_t spellcheck(String spellcheck) {
        return attr("spellcheck", spellcheck);
    }

    @Override
    public self_t style(String style) {
        return attr("style", style);
    }

    @Override
    public self_t tabindex(Integer tabindex) {
        return attr("tabindex", tabindex);
    }

    @Override
    public self_t title(String title) {
        return attr("title", title);
    }

    @Override
    public self_t translate(Boolean translate) {
        return attr("translate", translate);
    }

    // ________________________________________________________________________
    // ⇱ Part: Event Handlers
    //

    @Override
    public self_t onblur(String handler) {
        return attr("onblur", handler);
    }

    @Override
    public self_t oncancel(String handler) {
        return attr("oncancel", handler);
    }

    @Override
    public self_t oncanplay(String handler) {
        return attr("oncanplay", handler);
    }

    @Override
    public self_t oncanplaythrough(String handler) {
        return attr("oncanplaythrough", handler);
    }

    @Override
    public self_t onchange(String handler) {
        return attr("onchange", handler);
    }

    @Override
    public self_t onclick(String handler) {
        return attr("onclick", handler);
    }

    @Override
    public self_t onclose(String handler) {
        return attr("onclose", handler);
    }

    @Override
    public self_t oncuechange(String handler) {
        return attr("oncuechange", handler);
    }

    @Override
    public self_t ondblclick(String handler) {
        return attr("ondblclick", handler);
    }

    @Override
    public self_t ondrag(String handler) {
        return attr("ondrag", handler);
    }

    @Override
    public self_t ondragend(String handler) {
        return attr("ondragend", handler);
    }

    @Override
    public self_t ondragenter(String handler) {
        return attr("ondragenter", handler);
    }

    @Override
    public self_t ondragexit(String handler) {
        return attr("ondragexit", handler);
    }

    @Override
    public self_t ondragleave(String handler) {
        return attr("ondragleave", handler);
    }

    @Override
    public self_t ondragover(String handler) {
        return attr("ondragover", handler);
    }

    @Override
    public self_t ondragstart(String handler) {
        return attr("ondragstart", handler);
    }

    @Override
    public self_t ondrop(String handler) {
        return attr("ondrop", handler);
    }

    @Override
    public self_t ondurationchange(String handler) {
        return attr("ondurationchange", handler);
    }

    @Override
    public self_t onemptied(String handler) {
        return attr("onemptied", handler);
    }

    @Override
    public self_t onended(String handler) {
        return attr("onended", handler);
    }

    @Override
    public self_t onerror(String handler) {
        return attr("onerror", handler);
    }

    @Override
    public self_t onfocus(String handler) {
        return attr("onfocus", handler);
    }

    @Override
    public self_t oninput(String handler) {
        return attr("oninput", handler);
    }

    @Override
    public self_t oninvalid(String handler) {
        return attr("oninvalid", handler);
    }

    @Override
    public self_t onkeydown(String handler) {
        return attr("onkeydown", handler);
    }

    @Override
    public self_t onkeypress(String handler) {
        return attr("onkeypress", handler);
    }

    @Override
    public self_t onkeyup(String handler) {
        return attr("onkeyup", handler);
    }

    @Override
    public self_t onload(String handler) {
        return attr("onload", handler);
    }

    @Override
    public self_t onloadeddata(String handler) {
        return attr("onloadeddata", handler);
    }

    @Override
    public self_t onloadedmetadata(String handler) {
        return attr("onloadedmetadata", handler);
    }

    @Override
    public self_t onloadstart(String handler) {
        return attr("onloadstart", handler);
    }

    @Override
    public self_t onmousedown(String handler) {
        return attr("onmousedown", handler);
    }

    @Override
    public self_t onmouseenter(String handler) {
        return attr("onmouseenter", handler);
    }

    @Override
    public self_t onmouseleave(String handler) {
        return attr("onmouseleave", handler);
    }

    @Override
    public self_t onmousemove(String handler) {
        return attr("onmousemove", handler);
    }

    @Override
    public self_t onmouseout(String handler) {
        return attr("onmouseout", handler);
    }

    @Override
    public self_t onmouseover(String handler) {
        return attr("onmouseover", handler);
    }

    @Override
    public self_t onmouseup(String handler) {
        return attr("onmouseup", handler);
    }

    @Override
    public self_t onmousewheel(String handler) {
        return attr("onmousewheel", handler);
    }

    @Override
    public self_t onpause(String handler) {
        return attr("onpause", handler);
    }

    @Override
    public self_t onplay(String handler) {
        return attr("onplay", handler);
    }

    @Override
    public self_t onplaying(String handler) {
        return attr("onplaying", handler);
    }

    @Override
    public self_t onprogress(String handler) {
        return attr("onprogress", handler);
    }

    @Override
    public self_t onratechange(String handler) {
        return attr("onratechange", handler);
    }

    @Override
    public self_t onreset(String handler) {
        return attr("onreset", handler);
    }

    @Override
    public self_t onresize(String handler) {
        return attr("onresize", handler);
    }

    @Override
    public self_t onscroll(String handler) {
        return attr("onscroll", handler);
    }

    @Override
    public self_t onseeked(String handler) {
        return attr("onseeked", handler);
    }

    @Override
    public self_t onseeking(String handler) {
        return attr("onseeking", handler);
    }

    @Override
    public self_t onselect(String handler) {
        return attr("onselect", handler);
    }

    @Override
    public self_t onshow(String handler) {
        return attr("onshow", handler);
    }

    @Override
    public self_t onstalled(String handler) {
        return attr("onstalled", handler);
    }

    @Override
    public self_t onsubmit(String handler) {
        return attr("onsubmit", handler);
    }

    @Override
    public self_t onsuspend(String handler) {
        return attr("onsuspend", handler);
    }

    @Override
    public self_t ontimeupdate(String handler) {
        return attr("ontimeupdate", handler);
    }

    @Override
    public self_t ontoggle(String handler) {
        return attr("ontoggle", handler);
    }

    @Override
    public self_t onvolumechange(String handler) {
        return attr("onvolumechange", handler);
    }

    @Override
    public self_t onwaiting(String handler) {
        return attr("onwaiting", handler);
    }

    public HtmlATag a() {
        return new HtmlATag(this, "a");
    }

    public HtmlAbbrTag abbr() {
        return new HtmlAbbrTag(this, "abbr");
    }

    public HtmlAddressTag address() {
        return new HtmlAddressTag(this, "address");
    }

    public HtmlAreaTag area() {
        return new HtmlAreaTag(this, "area");
    }

    public HtmlArticleTag article() {
        return new HtmlArticleTag(this, "article");
    }

    public HtmlAsideTag aside() {
        return new HtmlAsideTag(this, "aside");
    }

    public HtmlAudioTag audio() {
        return new HtmlAudioTag(this, "audio");
    }

    public HtmlBTag b() {
        return new HtmlBTag(this, "b");
    }

    public HtmlBaseTag base() {
        return new HtmlBaseTag(this, "base");
    }

    public HtmlBdiTag bdi() {
        return new HtmlBdiTag(this, "bdi");
    }

    public HtmlBdoTag bdo() {
        return new HtmlBdoTag(this, "bdo");
    }

    public HtmlBlockquoteTag blockquote() {
        return new HtmlBlockquoteTag(this, "blockquote");
    }

    public HtmlBodyTag body() {
        return new HtmlBodyTag(this, "body");
    }

    public HtmlBrTag br() {
        return new HtmlBrTag(this, "br");
    }

    public HtmlButtonTag button() {
        return new HtmlButtonTag(this, "button");
    }

    public HtmlCanvasTag canvas() {
        return new HtmlCanvasTag(this, "canvas");
    }

    public HtmlCaptionTag caption() {
        return new HtmlCaptionTag(this, "caption");
    }

    public HtmlCiteTag cite() {
        return new HtmlCiteTag(this, "cite");
    }

    public HtmlCodeTag code() {
        return new HtmlCodeTag(this, "code");
    }

    public HtmlColTag col() {
        return new HtmlColTag(this, "col");
    }

    public HtmlColgroupTag colgroup() {
        return new HtmlColgroupTag(this, "colgroup");
    }

    public HtmlDataTag data() {
        return new HtmlDataTag(this, "data");
    }

    public HtmlDatalistTag datalist() {
        return new HtmlDatalistTag(this, "datalist");
    }

    public HtmlDdTag dd() {
        return new HtmlDdTag(this, "dd");
    }

    public HtmlDelTag del() {
        return new HtmlDelTag(this, "del");
    }

    public HtmlDetailsTag details() {
        return new HtmlDetailsTag(this, "details");
    }

    public HtmlDfnTag dfn() {
        return new HtmlDfnTag(this, "dfn");
    }

    public HtmlDialogTag dialog() {
        return new HtmlDialogTag(this, "dialog");
    }

    public HtmlDivTag div() {
        return new HtmlDivTag(this, "div");
    }

    public HtmlDlTag dl() {
        return new HtmlDlTag(this, "dl");
    }

    public HtmlDtTag dt() {
        return new HtmlDtTag(this, "dt");
    }

    public HtmlEmTag em() {
        return new HtmlEmTag(this, "em");
    }

    public HtmlEmbedTag embed() {
        return new HtmlEmbedTag(this, "embed");
    }

    public HtmlFieldsetTag fieldset() {
        return new HtmlFieldsetTag(this, "fieldset");
    }

    public HtmlFigcaptionTag figcaption() {
        return new HtmlFigcaptionTag(this, "figcaption");
    }

    public HtmlFigureTag figure() {
        return new HtmlFigureTag(this, "figure");
    }

    public HtmlFooterTag footer() {
        return new HtmlFooterTag(this, "footer");
    }

    public HtmlFormTag form() {
        return new HtmlFormTag(this, "form");
    }

    public HtmlH1Tag h1() {
        return new HtmlH1Tag(this, "h1");
    }

    public HtmlH2Tag h2() {
        return new HtmlH2Tag(this, "h2");
    }

    public HtmlH3Tag h3() {
        return new HtmlH3Tag(this, "h3");
    }

    public HtmlH4Tag h4() {
        return new HtmlH4Tag(this, "h4");
    }

    public HtmlH5Tag h5() {
        return new HtmlH5Tag(this, "h5");
    }

    public HtmlH6Tag h6() {
        return new HtmlH6Tag(this, "h6");
    }

    public HtmlHeadTag head() {
        return new HtmlHeadTag(this, "head");
    }

    public HtmlHeaderTag header() {
        return new HtmlHeaderTag(this, "header");
    }

    public HtmlHrTag hr() {
        return new HtmlHrTag(this, "hr");
    }

    public HtmlHtmlTag html() {
        return new HtmlHtmlTag(this, "html");
    }

    public HtmlITag i() {
        return new HtmlITag(this, "i");
    }

    public HtmlIframeTag iframe() {
        return new HtmlIframeTag(this, "iframe");
    }

    public HtmlImgTag img() {
        return new HtmlImgTag(this, "img");
    }

    public HtmlInputTag input() {
        return new HtmlInputTag(this, "input");
    }

    public HtmlInsTag ins() {
        return new HtmlInsTag(this, "ins");
    }

    public HtmlKbdTag kbd() {
        return new HtmlKbdTag(this, "kbd");
    }

    public HtmlKeygenTag keygen() {
        return new HtmlKeygenTag(this, "keygen");
    }

    public HtmlLabelTag label() {
        return new HtmlLabelTag(this, "label");
    }

    public HtmlLegendTag legend() {
        return new HtmlLegendTag(this, "legend");
    }

    public HtmlLiTag li() {
        return new HtmlLiTag(this, "li");
    }

    public HtmlLinkTag link() {
        return new HtmlLinkTag(this, "link");
    }

    public HtmlMainTag main() {
        return new HtmlMainTag(this, "main");
    }

    public HtmlMapTag map() {
        return new HtmlMapTag(this, "map");
    }

    public HtmlMarkTag mark() {
        return new HtmlMarkTag(this, "mark");
    }

    public HtmlMetaTag meta() {
        return new HtmlMetaTag(this, "meta");
    }

    public HtmlMeterTag meter() {
        return new HtmlMeterTag(this, "meter");
    }

    public HtmlNavTag nav() {
        return new HtmlNavTag(this, "nav");
    }

    public HtmlNoscriptTag noscript() {
        return new HtmlNoscriptTag(this, "noscript");
    }

    public HtmlObjectTag object() {
        return new HtmlObjectTag(this, "object");
    }

    public HtmlOlTag ol() {
        return new HtmlOlTag(this, "ol");
    }

    public HtmlOptgroupTag optgroup() {
        return new HtmlOptgroupTag(this, "optgroup");
    }

    public HtmlOptionTag option() {
        return new HtmlOptionTag(this, "option");
    }

    public HtmlOutputTag output() {
        return new HtmlOutputTag(this, "output");
    }

    public HtmlPTag p() {
        return new HtmlPTag(this, "p");
    }

    public HtmlParamTag param() {
        return new HtmlParamTag(this, "param");
    }

    public HtmlPreTag pre() {
        return new HtmlPreTag(this, "pre");
    }

    public HtmlProgressTag progress() {
        return new HtmlProgressTag(this, "progress");
    }

    public HtmlQTag q() {
        return new HtmlQTag(this, "q");
    }

    public HtmlRbTag rb() {
        return new HtmlRbTag(this, "rb");
    }

    public HtmlRpTag rp() {
        return new HtmlRpTag(this, "rp");
    }

    public HtmlRtTag rt() {
        return new HtmlRtTag(this, "rt");
    }

    public HtmlRtcTag rtc() {
        return new HtmlRtcTag(this, "rtc");
    }

    public HtmlRubyTag ruby() {
        return new HtmlRubyTag(this, "ruby");
    }

    public HtmlSTag s() {
        return new HtmlSTag(this, "s");
    }

    public HtmlSampTag samp() {
        return new HtmlSampTag(this, "samp");
    }

    public HtmlScriptTag script() {
        return new HtmlScriptTag(this, "script");
    }

    public HtmlSectionTag section() {
        return new HtmlSectionTag(this, "section");
    }

    public HtmlSelectTag select() {
        return new HtmlSelectTag(this, "select");
    }

    public HtmlSmallTag small() {
        return new HtmlSmallTag(this, "small");
    }

    public HtmlSourceTag source() {
        return new HtmlSourceTag(this, "source");
    }

    public HtmlSpanTag span() {
        return new HtmlSpanTag(this, "span");
    }

    public HtmlStrongTag strong() {
        return new HtmlStrongTag(this, "strong");
    }

    public HtmlStyleTag style() {
        return new HtmlStyleTag(this, "style");
    }

    public HtmlSubandsupTag subandsup() {
        return new HtmlSubandsupTag(this, "subandsup");
    }

    public HtmlSummaryTag summary() {
        return new HtmlSummaryTag(this, "summary");
    }

    public HtmlTableTag table() {
        return new HtmlTableTag(this, "table");
    }

    public HtmlTbodyTag tbody() {
        return new HtmlTbodyTag(this, "tbody");
    }

    public HtmlTdTag td() {
        return new HtmlTdTag(this, "td");
    }

    public HtmlTemplateTag template() {
        return new HtmlTemplateTag(this, "template");
    }

    public HtmlTextareaTag textarea() {
        return new HtmlTextareaTag(this, "textarea");
    }

    public HtmlTfootTag tfoot() {
        return new HtmlTfootTag(this, "tfoot");
    }

    public HtmlThTag th() {
        return new HtmlThTag(this, "th");
    }

    public HtmlTheadTag thead() {
        return new HtmlTheadTag(this, "thead");
    }

    public HtmlTimeTag time() {
        return new HtmlTimeTag(this, "time");
    }

    public HtmlTitleTag title() {
        return new HtmlTitleTag(this, "title");
    }

    public HtmlTrTag tr() {
        return new HtmlTrTag(this, "tr");
    }

    public HtmlTrackTag track() {
        return new HtmlTrackTag(this, "track");
    }

    public HtmlUTag u() {
        return new HtmlUTag(this, "u");
    }

    public HtmlUlTag ul() {
        return new HtmlUlTag(this, "ul");
    }

    public HtmlVarTag var() {
        return new HtmlVarTag(this, "var");
    }

    public HtmlVideoTag video() {
        return new HtmlVideoTag(this, "video");
    }

    public HtmlWbrTag wbr() {
        return new HtmlWbrTag(this, "wbr");
    }

}
