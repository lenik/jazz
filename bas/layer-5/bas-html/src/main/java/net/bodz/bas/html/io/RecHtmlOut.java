package net.bodz.bas.html.io;

import net.bodz.bas.html.io.tag.*;
import net.bodz.bas.io.xml.RecXmlOut;
import net.bodz.bas.ui.css3.property.DirectionMode;

public class RecHtmlOut<self_t extends RecHtmlOut<self_t>>
        extends RecXmlOut<self_t>
        implements IHtmlOut {

    protected HtmlDoc doc;

    public RecHtmlOut(HtmlDoc doc) {
        super(doc.outputFormat);
        this.doc = doc;
    }

    protected RecHtmlOut(HtmlOutputFormat outputFormat) {
        super(outputFormat);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected self_t create() {
        return (self_t) new RecHtmlOut<>(doc);
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

    @Override
    public HtmlA a() {
        return new HtmlA(doc);
    }

    @Override
    public HtmlAbbr abbr() {
        return new HtmlAbbr(doc);
    }

    @Override
    public HtmlAddress address() {
        return new HtmlAddress(doc);
    }

    @Override
    public HtmlArea area() {
        return new HtmlArea(doc);
    }

    @Override
    public HtmlArticle article() {
        return new HtmlArticle(doc);
    }

    @Override
    public HtmlAside aside() {
        return new HtmlAside(doc);
    }

    @Override
    public HtmlAudio audio() {
        return new HtmlAudio(doc);
    }

    @Override
    public HtmlB b() {
        return new HtmlB(doc);
    }

    @Override
    public HtmlBase base() {
        return new HtmlBase(doc);
    }

    @Override
    public HtmlBdi bdi() {
        return new HtmlBdi(doc);
    }

    @Override
    public HtmlBdo bdo() {
        return new HtmlBdo(doc);
    }

    @Override
    public HtmlBlockquote blockquote() {
        return new HtmlBlockquote(doc);
    }

    @Override
    public HtmlBody body() {
        return new HtmlBody(doc);
    }

    @Override
    public HtmlBr br() {
        return new HtmlBr(doc);
    }

    @Override
    public HtmlButton button() {
        return new HtmlButton(doc);
    }

    @Override
    public HtmlCanvas canvas() {
        return new HtmlCanvas(doc);
    }

    @Override
    public HtmlCaption caption() {
        return new HtmlCaption(doc);
    }

    @Override
    public HtmlCite cite() {
        return new HtmlCite(doc);
    }

    @Override
    public HtmlCode code() {
        return new HtmlCode(doc);
    }

    @Override
    public HtmlCol col() {
        return new HtmlCol(doc);
    }

    @Override
    public HtmlColgroup colgroup() {
        return new HtmlColgroup(doc);
    }

    @Override
    public HtmlData data() {
        return new HtmlData(doc);
    }

    @Override
    public HtmlDatalist datalist() {
        return new HtmlDatalist(doc);
    }

    @Override
    public HtmlDd dd() {
        return new HtmlDd(doc);
    }

    @Override
    public HtmlDel del() {
        return new HtmlDel(doc);
    }

    @Override
    public HtmlDetails details() {
        return new HtmlDetails(doc);
    }

    @Override
    public HtmlDfn dfn() {
        return new HtmlDfn(doc);
    }

    @Override
    public HtmlDialog dialog() {
        return new HtmlDialog(doc);
    }

    @Override
    public HtmlDiv div() {
        return new HtmlDiv(doc);
    }

    @Override
    public HtmlDl dl() {
        return new HtmlDl(doc);
    }

    @Override
    public HtmlDt dt() {
        return new HtmlDt(doc);
    }

    @Override
    public HtmlEm em() {
        return new HtmlEm(doc);
    }

    @Override
    public HtmlEmbed embed() {
        return new HtmlEmbed(doc);
    }

    @Override
    public HtmlFieldset fieldset() {
        return new HtmlFieldset(doc);
    }

    @Override
    public HtmlFigcaption figcaption() {
        return new HtmlFigcaption(doc);
    }

    @Override
    public HtmlFigure figure() {
        return new HtmlFigure(doc);
    }

    @Override
    public HtmlFooter footer() {
        return new HtmlFooter(doc);
    }

    @Override
    public HtmlForm form() {
        return new HtmlForm(doc);
    }

    @Override
    public HtmlH1 h1() {
        return new HtmlH1(doc);
    }

    @Override
    public HtmlH2 h2() {
        return new HtmlH2(doc);
    }

    @Override
    public HtmlH3 h3() {
        return new HtmlH3(doc);
    }

    @Override
    public HtmlH4 h4() {
        return new HtmlH4(doc);
    }

    @Override
    public HtmlH5 h5() {
        return new HtmlH5(doc);
    }

    @Override
    public HtmlH6 h6() {
        return new HtmlH6(doc);
    }

    @Override
    public HtmlHead head() {
        return new HtmlHead(doc);
    }

    @Override
    public HtmlHeader header() {
        return new HtmlHeader(doc);
    }

    @Override
    public HtmlHr hr() {
        return new HtmlHr(doc);
    }

    @Override
    public HtmlHtml html() {
        return new HtmlHtml(doc);
    }

    @Override
    public HtmlI i() {
        return new HtmlI(doc);
    }

    @Override
    public HtmlIframe iframe() {
        return new HtmlIframe(doc);
    }

    @Override
    public HtmlImg img() {
        return new HtmlImg(doc);
    }

    @Override
    public HtmlInput input() {
        return new HtmlInput(doc);
    }

    @Override
    public HtmlIns ins() {
        return new HtmlIns(doc);
    }

    @Override
    public HtmlKbd kbd() {
        return new HtmlKbd(doc);
    }

    @Override
    public HtmlKeygen keygen() {
        return new HtmlKeygen(doc);
    }

    @Override
    public HtmlLabel label() {
        return new HtmlLabel(doc);
    }

    @Override
    public HtmlLegend legend() {
        return new HtmlLegend(doc);
    }

    @Override
    public HtmlLi li() {
        return new HtmlLi(doc);
    }

    @Override
    public HtmlLink link() {
        return new HtmlLink(doc);
    }

    @Override
    public HtmlMain main() {
        return new HtmlMain(doc);
    }

    @Override
    public HtmlMap map() {
        return new HtmlMap(doc);
    }

    @Override
    public HtmlMark mark() {
        return new HtmlMark(doc);
    }

    @Override
    public HtmlMeta meta() {
        return new HtmlMeta(doc);
    }

    @Override
    public HtmlMeter meter() {
        return new HtmlMeter(doc);
    }

    @Override
    public HtmlNav nav() {
        return new HtmlNav(doc);
    }

    @Override
    public HtmlNoscript noscript() {
        return new HtmlNoscript(doc);
    }

    @Override
    public HtmlObject object() {
        return new HtmlObject(doc);
    }

    @Override
    public HtmlOl ol() {
        return new HtmlOl(doc);
    }

    @Override
    public HtmlOptgroup optgroup() {
        return new HtmlOptgroup(doc);
    }

    @Override
    public HtmlOption option() {
        return new HtmlOption(doc);
    }

    @Override
    public HtmlOutput output() {
        return new HtmlOutput(doc);
    }

    @Override
    public HtmlP p() {
        return new HtmlP(doc);
    }

    @Override
    public HtmlParam param() {
        return new HtmlParam(doc);
    }

    @Override
    public HtmlPre pre() {
        return new HtmlPre(doc);
    }

    @Override
    public HtmlProgress progress() {
        return new HtmlProgress(doc);
    }

    @Override
    public HtmlQ q() {
        return new HtmlQ(doc);
    }

    @Override
    public HtmlRb rb() {
        return new HtmlRb(doc);
    }

    @Override
    public HtmlRp rp() {
        return new HtmlRp(doc);
    }

    @Override
    public HtmlRt rt() {
        return new HtmlRt(doc);
    }

    @Override
    public HtmlRtc rtc() {
        return new HtmlRtc(doc);
    }

    @Override
    public HtmlRuby ruby() {
        return new HtmlRuby(doc);
    }

    @Override
    public HtmlS s() {
        return new HtmlS(doc);
    }

    @Override
    public HtmlSamp samp() {
        return new HtmlSamp(doc);
    }

    @Override
    public HtmlScript script() {
        return new HtmlScript(doc);
    }

    @Override
    public HtmlSection section() {
        return new HtmlSection(doc);
    }

    @Override
    public HtmlSelect select() {
        return new HtmlSelect(doc);
    }

    @Override
    public HtmlSmall small() {
        return new HtmlSmall(doc);
    }

    @Override
    public HtmlSource source() {
        return new HtmlSource(doc);
    }

    @Override
    public HtmlSpan span() {
        return new HtmlSpan(doc);
    }

    @Override
    public HtmlStrong strong() {
        return new HtmlStrong(doc);
    }

    @Override
    public HtmlStyle style() {
        return new HtmlStyle(doc);
    }

    @Override
    public HtmlSubandsup subandsup() {
        return new HtmlSubandsup(doc);
    }

    @Override
    public HtmlSummary summary() {
        return new HtmlSummary(doc);
    }

    @Override
    public HtmlTable table() {
        return new HtmlTable(doc);
    }

    @Override
    public HtmlTbody tbody() {
        return new HtmlTbody(doc);
    }

    @Override
    public HtmlTd td() {
        return new HtmlTd(doc);
    }

    @Override
    public HtmlTemplate template() {
        return new HtmlTemplate(doc);
    }

    @Override
    public HtmlTextarea textarea() {
        return new HtmlTextarea(doc);
    }

    @Override
    public HtmlTfoot tfoot() {
        return new HtmlTfoot(doc);
    }

    @Override
    public HtmlTh th() {
        return new HtmlTh(doc);
    }

    @Override
    public HtmlThead thead() {
        return new HtmlThead(doc);
    }

    @Override
    public HtmlTime time() {
        return new HtmlTime(doc);
    }

    @Override
    public HtmlTitle title() {
        return new HtmlTitle(doc);
    }

    @Override
    public HtmlTr tr() {
        return new HtmlTr(doc);
    }

    @Override
    public HtmlTrack track() {
        return new HtmlTrack(doc);
    }

    @Override
    public HtmlU u() {
        return new HtmlU(doc);
    }

    @Override
    public HtmlUl ul() {
        return new HtmlUl(doc);
    }

    @Override
    public HtmlVar var() {
        return new HtmlVar(doc);
    }

    @Override
    public HtmlVideo video() {
        return new HtmlVideo(doc);
    }

    @Override
    public HtmlWbr wbr() {
        return new HtmlWbr(doc);
    }

    // ________________________________________________________________________
    // ⇱ Part: Shortcuts
    //

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
