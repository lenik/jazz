package net.bodz.bas.html.io;

import net.bodz.bas.html.io.tag.*;
import net.bodz.bas.io.xml.AbstractRecXmlOut;
import net.bodz.bas.ui.css3.property.DirectionMode;

public abstract class AbstractRecHtmlOut<self_t extends AbstractRecHtmlOut<self_t>>
        extends AbstractRecXmlOut<AbstractRecHtmlOut<?>, self_t>
        implements IHtmlOut {

    protected final HtmlDoc doc;

    public AbstractRecHtmlOut(HtmlDoc doc) {
        super(doc);
        this.doc = doc;
    }

    @Override
    public HtmlDoc getDoc() {
        return doc;
    }

    @Override
    public RecHtmlOut begin(String name) {
        return begin(name, new RecHtmlOut(doc));
    }

    @Override
    protected <T extends AbstractRecHtmlOut<?>> T begin(String name, T node) {
        node.pretag(this);
        return super.begin(name, node);
    }

    protected void pretag(IHtmlOut parent) {
    }

    // ________________________________________________________________________
    // ⇱ Part: Common Attributes
    //

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

    // ________________________________________________________________________
    // ⇱ Part: Elements
    //

    public HtmlA a() {
        return begin("a", new HtmlA(doc));
    }

    public HtmlAbbr abbr() {
        return begin("abbr", new HtmlAbbr(doc));
    }

    public HtmlAddress address() {
        return begin("address", new HtmlAddress(doc));
    }

    public HtmlArea area() {
        return begin("area", new HtmlArea(doc));
    }

    public HtmlArticle article() {
        return begin("article", new HtmlArticle(doc));
    }

    public HtmlAside aside() {
        return begin("aside", new HtmlAside(doc));
    }

    public HtmlAudio audio() {
        return begin("audio", new HtmlAudio(doc));
    }

    public HtmlB b() {
        return begin("b", new HtmlB(doc));
    }

    public HtmlBase base() {
        return begin("base", new HtmlBase(doc));
    }

    public HtmlBdi bdi() {
        return begin("bdi", new HtmlBdi(doc));
    }

    public HtmlBdo bdo() {
        return begin("bdo", new HtmlBdo(doc));
    }

    public HtmlBlockquote blockquote() {
        return begin("blockquote", new HtmlBlockquote(doc));
    }

    public HtmlBody body() {
        return begin("body", new HtmlBody(doc));
    }

    public HtmlBr br() {
        return begin("br", new HtmlBr(doc));
    }

    public HtmlButton button() {
        return begin("button", new HtmlButton(doc));
    }

    public HtmlCanvas canvas() {
        return begin("canvas", new HtmlCanvas(doc));
    }

    public HtmlCaption caption() {
        return begin("caption", new HtmlCaption(doc));
    }

    public HtmlCite cite() {
        return begin("cite", new HtmlCite(doc));
    }

    public HtmlCode code() {
        return begin("code", new HtmlCode(doc));
    }

    public HtmlCol col() {
        return begin("col", new HtmlCol(doc));
    }

    public HtmlColgroup colgroup() {
        return begin("colgroup", new HtmlColgroup(doc));
    }

    public HtmlData data() {
        return begin("data", new HtmlData(doc));
    }

    public HtmlDatalist datalist() {
        return begin("datalist", new HtmlDatalist(doc));
    }

    public HtmlDd dd() {
        return begin("dd", new HtmlDd(doc));
    }

    public HtmlDel del() {
        return begin("del", new HtmlDel(doc));
    }

    public HtmlDfn dfn() {
        return begin("dfn", new HtmlDfn(doc));
    }

    public HtmlDiv div() {
        return begin("div", new HtmlDiv(doc));
    }

    public HtmlDl dl() {
        return begin("dl", new HtmlDl(doc));
    }

    public HtmlDt dt() {
        return begin("dt", new HtmlDt(doc));
    }

    public HtmlEm em() {
        return begin("em", new HtmlEm(doc));
    }

    public HtmlEmbed embed() {
        return begin("embed", new HtmlEmbed(doc));
    }

    public HtmlFieldset fieldset() {
        return begin("fieldset", new HtmlFieldset(doc));
    }

    public HtmlFigcaption figcaption() {
        return begin("figcaption", new HtmlFigcaption(doc));
    }

    public HtmlFigure figure() {
        return begin("figure", new HtmlFigure(doc));
    }

    public HtmlFooter footer() {
        return begin("footer", new HtmlFooter(doc));
    }

    public HtmlForm form() {
        return begin("form", new HtmlForm(doc));
    }

    public HtmlH1 h1() {
        return begin("h1", new HtmlH1(doc));
    }

    public HtmlH2 h2() {
        return begin("h2", new HtmlH2(doc));
    }

    public HtmlH3 h3() {
        return begin("h3", new HtmlH3(doc));
    }

    public HtmlH4 h4() {
        return begin("h4", new HtmlH4(doc));
    }

    public HtmlH5 h5() {
        return begin("h5", new HtmlH5(doc));
    }

    public HtmlH6 h6() {
        return begin("h6", new HtmlH6(doc));
    }

    public HtmlHead head() {
        return begin("head", new HtmlHead(doc));
    }

    public HtmlHeader header() {
        return begin("header", new HtmlHeader(doc));
    }

    public HtmlHr hr() {
        return begin("hr", new HtmlHr(doc));
    }

    public HtmlHtml html() {
        return begin("html", new HtmlHtml(doc));
    }

    public HtmlI i() {
        return begin("i", new HtmlI(doc));
    }

    public HtmlIframe iframe() {
        return begin("iframe", new HtmlIframe(doc));
    }

    public HtmlImg img() {
        return begin("img", new HtmlImg(doc));
    }

    public HtmlInput input() {
        return begin("input", new HtmlInput(doc));
    }

    public HtmlIns ins() {
        return begin("ins", new HtmlIns(doc));
    }

    public HtmlKbd kbd() {
        return begin("kbd", new HtmlKbd(doc));
    }

    public HtmlKeygen keygen() {
        return begin("keygen", new HtmlKeygen(doc));
    }

    public HtmlLabel label() {
        return begin("label", new HtmlLabel(doc));
    }

    public HtmlLegend legend() {
        return begin("legend", new HtmlLegend(doc));
    }

    public HtmlLi li() {
        return begin("li", new HtmlLi(doc));
    }

    public HtmlLink link() {
        return begin("link", new HtmlLink(doc));
    }

    public HtmlMain main() {
        return begin("main", new HtmlMain(doc));
    }

    public HtmlMap map() {
        return begin("map", new HtmlMap(doc));
    }

    public HtmlMark mark() {
        return begin("mark", new HtmlMark(doc));
    }

    public HtmlMeta meta() {
        return begin("meta", new HtmlMeta(doc));
    }

    public HtmlMeter meter() {
        return begin("meter", new HtmlMeter(doc));
    }

    public HtmlNav nav() {
        return begin("nav", new HtmlNav(doc));
    }

    public HtmlNoscript noscript() {
        return begin("noscript", new HtmlNoscript(doc));
    }

    public HtmlObject object() {
        return begin("object", new HtmlObject(doc));
    }

    public HtmlOl ol() {
        return begin("ol", new HtmlOl(doc));
    }

    public HtmlOptgroup optgroup() {
        return begin("optgroup", new HtmlOptgroup(doc));
    }

    public HtmlOption option() {
        return begin("option", new HtmlOption(doc));
    }

    public HtmlOutput output() {
        return begin("output", new HtmlOutput(doc));
    }

    public HtmlP p() {
        return begin("p", new HtmlP(doc));
    }

    public HtmlParam param() {
        return begin("param", new HtmlParam(doc));
    }

    public HtmlPre pre() {
        return begin("pre", new HtmlPre(doc));
    }

    public HtmlProgress progress() {
        return begin("progress", new HtmlProgress(doc));
    }

    public HtmlQ q() {
        return begin("q", new HtmlQ(doc));
    }

    public HtmlRb rb() {
        return begin("rb", new HtmlRb(doc));
    }

    public HtmlRp rp() {
        return begin("rp", new HtmlRp(doc));
    }

    public HtmlRt rt() {
        return begin("rt", new HtmlRt(doc));
    }

    public HtmlRtc rtc() {
        return begin("rtc", new HtmlRtc(doc));
    }

    public HtmlRuby ruby() {
        return begin("ruby", new HtmlRuby(doc));
    }

    public HtmlS s() {
        return begin("s", new HtmlS(doc));
    }

    public HtmlSamp samp() {
        return begin("samp", new HtmlSamp(doc));
    }

    public HtmlScript script() {
        return begin("script", new HtmlScript(doc));
    }

    public HtmlSection section() {
        return begin("section", new HtmlSection(doc));
    }

    public HtmlSelect select() {
        return begin("select", new HtmlSelect(doc));
    }

    public HtmlSmall small() {
        return begin("small", new HtmlSmall(doc));
    }

    public HtmlSource source() {
        return begin("source", new HtmlSource(doc));
    }

    public HtmlSpan span() {
        return begin("span", new HtmlSpan(doc));
    }

    public HtmlStrong strong() {
        return begin("strong", new HtmlStrong(doc));
    }

    public HtmlStyle style() {
        return begin("style", new HtmlStyle(doc));
    }

    public HtmlSubandsup subandsup() {
        return begin("subandsup", new HtmlSubandsup(doc));
    }

    public HtmlTable table() {
        return begin("table", new HtmlTable(doc));
    }

    public HtmlTbody tbody() {
        return begin("tbody", new HtmlTbody(doc));
    }

    public HtmlTd td() {
        return begin("td", new HtmlTd(doc));
    }

    public HtmlTemplate template() {
        return begin("template", new HtmlTemplate(doc));
    }

    public HtmlTextarea textarea() {
        return begin("textarea", new HtmlTextarea(doc));
    }

    public HtmlTfoot tfoot() {
        return begin("tfoot", new HtmlTfoot(doc));
    }

    public HtmlTh th() {
        return begin("th", new HtmlTh(doc));
    }

    public HtmlThead thead() {
        return begin("thead", new HtmlThead(doc));
    }

    public HtmlTime time() {
        return begin("time", new HtmlTime(doc));
    }

    public HtmlTitle title() {
        return begin("title", new HtmlTitle(doc));
    }

    public HtmlTr tr() {
        return begin("tr", new HtmlTr(doc));
    }

    public HtmlTrack track() {
        return begin("track", new HtmlTrack(doc));
    }

    public HtmlU u() {
        return begin("u", new HtmlU(doc));
    }

    public HtmlUl ul() {
        return begin("ul", new HtmlUl(doc));
    }

    public HtmlVar var() {
        return begin("var", new HtmlVar(doc));
    }

    public HtmlVideo video() {
        return begin("video", new HtmlVideo(doc));
    }

    public HtmlWbr wbr() {
        return begin("wbr", new HtmlWbr(doc));
    }

    @Override
    public HtmlDetails details() {
        return begin("details", new HtmlDetails(doc));
    }

    @Override
    public HtmlDialog dialog() {
        return begin("dialog", new HtmlDialog(doc));
    }

    @Override
    public HtmlSummary summary() {
        return begin("summary", new HtmlSummary(doc));
    }

    // ________________________________________________________________________
    // ⇱ Part: Shortcuts
    //

    public self_t class_(String... classes) {
        StringBuilder sb = new StringBuilder();
        for (String c : classes) {
            if (c != null && !c.isEmpty()) {
                if (sb.length() != 0)
                    sb.append(' ');
                sb.append(c);
            }
        }
        String s = sb.toString();
        // if (!s.isEmpty())
        return class_(s);
    }

    public self_t bText(Object bText) {
        return bText(bText, null);
    }

    @SuppressWarnings("unchecked")
    public self_t bText(Object bText, String bClass) {
        HtmlB b = b();
        if (bClass != null)
            b.class_(bClass);
        b.text(bText);
        b.end();
        return (self_t) this;
    }

    public self_t iText(Object iText) {
        return iText(iText, null);
    }

    @SuppressWarnings("unchecked")
    public self_t iText(Object iText, String iClass) {
        HtmlI i = i();
        if (iClass != null)
            i.class_(iClass);
        i.text(iText);
        i.end();
        return (self_t) this;
    }

    public self_t aText(Object aText) {
        return aText(aText, "#");
    }

    @SuppressWarnings("unchecked")
    public self_t aText(Object aText, String aHref) {
        a().href(aHref).text(aText).end();
        return (self_t) this;
    }

}
