package net.bodz.bas.html.dom;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.html.dom.tag.*;
import net.bodz.bas.ui.css3.property.DirectionMode;
import net.bodz.bas.xml.dom.IXmlTag;
import net.bodz.bas.xml.dom.MutableXmlTag;

@SuppressWarnings("unchecked")
public class MutableHtmlTag<self_t extends IHtmlTag>
        extends MutableXmlTag<self_t>
        implements IHtmlTag {

    protected MutableHtmlTag(IXmlTag parent) {
        super(parent);
    }

    public MutableHtmlTag(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    @Override
    public IHtmlTag insert(String tagName) {
        return new HtmlTag(this, tagName);
    }

    @Override
    public self_t id(String id) {
        MutableHtmlDoc doc = (MutableHtmlDoc) getRoot();
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

    public self_t addClass(String... classes) {
        Set<String> mark = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        String old = getAttributeMap().get("class");
        if (old != null) {
            mark.add(old);
            sb.append(old);
        }
        for (String class_ : classes)
            if (mark.add(class_)) {
                if (sb.length() != 0)
                    sb.append(" ");
                sb.append(class_);
            }
        return attr("class", sb.toString());
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

    public self_t bText(Object bText) {
        return bText(bText, null);
    }

    public self_t bText(Object bText, String bClass) {
        MutableB b = b();
        if (bClass != null)
            b.class_(bClass);
        b.text(bText);
        return (self_t) this;
    }

    public self_t iText(Object iText) {
        return iText(iText, null);
    }

    public self_t iText(Object iText, String iClass) {
        MutableI i = i();
        if (iClass != null)
            i.class_(iClass);
        i.text(iText);
        return (self_t) this;
    }

    public self_t aText(Object aText) {
        return aText(aText, "#");
    }

    public self_t aText(Object aText, String aHref) {
        a().href(aHref).text(aText);
        return (self_t) this;
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

    public MutableA a() {
        return new MutableA(this, "a");
    }

    public MutableAbbr abbr() {
        return new MutableAbbr(this, "abbr");
    }

    public MutableAddress address() {
        return new MutableAddress(this, "address");
    }

    public MutableArea area() {
        return new MutableArea(this, "area");
    }

    public MutableArticle article() {
        return new MutableArticle(this, "article");
    }

    public MutableAside aside() {
        return new MutableAside(this, "aside");
    }

    public MutableAudio audio() {
        return new MutableAudio(this, "audio");
    }

    public MutableB b() {
        return new MutableB(this, "b");
    }

    public MutableBase base() {
        return new MutableBase(this, "base");
    }

    public MutableBdi bdi() {
        return new MutableBdi(this, "bdi");
    }

    public MutableBdo bdo() {
        return new MutableBdo(this, "bdo");
    }

    public MutableBlockquote blockquote() {
        return new MutableBlockquote(this, "blockquote");
    }

    public MutableBody body() {
        return new MutableBody(this, "body");
    }

    public MutableBr br() {
        return new MutableBr(this, "br");
    }

    public MutableButton button() {
        return new MutableButton(this, "button");
    }

    public MutableCanvas canvas() {
        return new MutableCanvas(this, "canvas");
    }

    public MutableCaption caption() {
        return new MutableCaption(this, "caption");
    }

    public MutableCite cite() {
        return new MutableCite(this, "cite");
    }

    public MutableCode code() {
        return new MutableCode(this, "code");
    }

    public MutableCol col() {
        return new MutableCol(this, "col");
    }

    public MutableColgroup colgroup() {
        return new MutableColgroup(this, "colgroup");
    }

    public MutableData data() {
        return new MutableData(this, "data");
    }

    public MutableDatalist datalist() {
        return new MutableDatalist(this, "datalist");
    }

    public MutableDd dd() {
        return new MutableDd(this, "dd");
    }

    public MutableDel del() {
        return new MutableDel(this, "del");
    }

    public MutableDetails details() {
        return new MutableDetails(this, "details");
    }

    public MutableDfn dfn() {
        return new MutableDfn(this, "dfn");
    }

    public MutableDialog dialog() {
        return new MutableDialog(this, "dialog");
    }

    public MutableDiv div() {
        return new MutableDiv(this, "div");
    }

    public MutableDl dl() {
        return new MutableDl(this, "dl");
    }

    public MutableDt dt() {
        return new MutableDt(this, "dt");
    }

    public MutableEm em() {
        return new MutableEm(this, "em");
    }

    public MutableEmbed embed() {
        return new MutableEmbed(this, "embed");
    }

    public MutableFieldset fieldset() {
        return new MutableFieldset(this, "fieldset");
    }

    public MutableFigcaption figcaption() {
        return new MutableFigcaption(this, "figcaption");
    }

    public MutableFigure figure() {
        return new MutableFigure(this, "figure");
    }

    public MutableFooter footer() {
        return new MutableFooter(this, "footer");
    }

    public MutableForm form() {
        return new MutableForm(this, "form");
    }

    public MutableH1 h1() {
        return new MutableH1(this, "h1");
    }

    public MutableH2 h2() {
        return new MutableH2(this, "h2");
    }

    public MutableH3 h3() {
        return new MutableH3(this, "h3");
    }

    public MutableH4 h4() {
        return new MutableH4(this, "h4");
    }

    public MutableH5 h5() {
        return new MutableH5(this, "h5");
    }

    public MutableH6 h6() {
        return new MutableH6(this, "h6");
    }

    public MutableHead head() {
        return new MutableHead(this, "head");
    }

    public MutableHeader header() {
        return new MutableHeader(this, "header");
    }

    public MutableHr hr() {
        return new MutableHr(this, "hr");
    }

    public MutableHtml html() {
        return new MutableHtml(this, "html");
    }

    public MutableI i() {
        return new MutableI(this, "i");
    }

    public MutableIframe iframe() {
        return new MutableIframe(this, "iframe");
    }

    public MutableImg img() {
        return new MutableImg(this, "img");
    }

    public MutableInput input() {
        return new MutableInput(this, "input");
    }

    public MutableIns ins() {
        return new MutableIns(this, "ins");
    }

    public MutableKbd kbd() {
        return new MutableKbd(this, "kbd");
    }

    public MutableKeygen keygen() {
        return new MutableKeygen(this, "keygen");
    }

    public MutableLabel label() {
        return new MutableLabel(this, "label");
    }

    public MutableLegend legend() {
        return new MutableLegend(this, "legend");
    }

    public MutableLi li() {
        return new MutableLi(this, "li");
    }

    public MutableLink link() {
        return new MutableLink(this, "link");
    }

    public MutableMain main() {
        return new MutableMain(this, "main");
    }

    public MutableMap map() {
        return new MutableMap(this, "map");
    }

    public MutableMark mark() {
        return new MutableMark(this, "mark");
    }

    public MutableMeta meta() {
        return new MutableMeta(this, "meta");
    }

    public MutableMeter meter() {
        return new MutableMeter(this, "meter");
    }

    public MutableNav nav() {
        return new MutableNav(this, "nav");
    }

    public MutableNoscript noscript() {
        return new MutableNoscript(this, "noscript");
    }

    public MutableObject object() {
        return new MutableObject(this, "object");
    }

    public MutableOl ol() {
        return new MutableOl(this, "ol");
    }

    public MutableOptgroup optgroup() {
        return new MutableOptgroup(this, "optgroup");
    }

    public MutableOption option() {
        return new MutableOption(this, "option");
    }

    public MutableOutput output() {
        return new MutableOutput(this, "output");
    }

    public MutableP p() {
        return new MutableP(this, "p");
    }

    public MutableParam param() {
        return new MutableParam(this, "param");
    }

    public MutablePre pre() {
        return new MutablePre(this, "pre");
    }

    public MutableProgress progress() {
        return new MutableProgress(this, "progress");
    }

    public MutableQ q() {
        return new MutableQ(this, "q");
    }

    public MutableRb rb() {
        return new MutableRb(this, "rb");
    }

    public MutableRp rp() {
        return new MutableRp(this, "rp");
    }

    public MutableRt rt() {
        return new MutableRt(this, "rt");
    }

    public MutableRtc rtc() {
        return new MutableRtc(this, "rtc");
    }

    public MutableRuby ruby() {
        return new MutableRuby(this, "ruby");
    }

    public MutableS s() {
        return new MutableS(this, "s");
    }

    public MutableSamp samp() {
        return new MutableSamp(this, "samp");
    }

    public MutableScript script() {
        return new MutableScript(this, "script");
    }

    public MutableSection section() {
        return new MutableSection(this, "section");
    }

    public MutableSelect select() {
        return new MutableSelect(this, "select");
    }

    public MutableSmall small() {
        return new MutableSmall(this, "small");
    }

    public MutableSource source() {
        return new MutableSource(this, "source");
    }

    public MutableSpan span() {
        return new MutableSpan(this, "span");
    }

    public MutableStrong strong() {
        return new MutableStrong(this, "strong");
    }

    public MutableStyle style() {
        return new MutableStyle(this, "style");
    }

    public MutableSubandsup subandsup() {
        return new MutableSubandsup(this, "subandsup");
    }

    public MutableSummary summary() {
        return new MutableSummary(this, "summary");
    }

    public MutableTable table() {
        return new MutableTable(this, "table");
    }

    public MutableTbody tbody() {
        return new MutableTbody(this, "tbody");
    }

    public MutableTd td() {
        return new MutableTd(this, "td");
    }

    public MutableTemplate template() {
        return new MutableTemplate(this, "template");
    }

    public MutableTextarea textarea() {
        return new MutableTextarea(this, "textarea");
    }

    public MutableTfoot tfoot() {
        return new MutableTfoot(this, "tfoot");
    }

    public MutableTh th() {
        return new MutableTh(this, "th");
    }

    public MutableThead thead() {
        return new MutableThead(this, "thead");
    }

    public MutableTime time() {
        return new MutableTime(this, "time");
    }

    public MutableTitle title() {
        return new MutableTitle(this, "title");
    }

    public MutableTr tr() {
        return new MutableTr(this, "tr");
    }

    public MutableTrack track() {
        return new MutableTrack(this, "track");
    }

    public MutableU u() {
        return new MutableU(this, "u");
    }

    public MutableUl ul() {
        return new MutableUl(this, "ul");
    }

    public MutableVar var() {
        return new MutableVar(this, "var");
    }

    public MutableVideo video() {
        return new MutableVideo(this, "video");
    }

    public MutableWbr wbr() {
        return new MutableWbr(this, "wbr");
    }

}
