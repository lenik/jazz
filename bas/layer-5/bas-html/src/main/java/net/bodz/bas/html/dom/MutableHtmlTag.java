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
        return new MutableA(this);
    }

    public MutableAbbr abbr() {
        return new MutableAbbr(this);
    }

    public MutableAddress address() {
        return new MutableAddress(this);
    }

    public MutableArea area() {
        return new MutableArea(this);
    }

    public MutableArticle article() {
        return new MutableArticle(this);
    }

    public MutableAside aside() {
        return new MutableAside(this);
    }

    public MutableAudio audio() {
        return new MutableAudio(this);
    }

    public MutableB b() {
        return new MutableB(this);
    }

    public MutableBase base() {
        return new MutableBase(this);
    }

    public MutableBdi bdi() {
        return new MutableBdi(this);
    }

    public MutableBdo bdo() {
        return new MutableBdo(this);
    }

    public MutableBlockquote blockquote() {
        return new MutableBlockquote(this);
    }

    public MutableBody body() {
        return new MutableBody(this);
    }

    public MutableBr br() {
        return new MutableBr(this);
    }

    public MutableButton button() {
        return new MutableButton(this);
    }

    public MutableCanvas canvas() {
        return new MutableCanvas(this);
    }

    public MutableCaption caption() {
        return new MutableCaption(this);
    }

    public MutableCite cite() {
        return new MutableCite(this);
    }

    public MutableCode code() {
        return new MutableCode(this);
    }

    public MutableCol col() {
        return new MutableCol(this);
    }

    public MutableColgroup colgroup() {
        return new MutableColgroup(this);
    }

    public MutableData data() {
        return new MutableData(this);
    }

    public MutableDatalist datalist() {
        return new MutableDatalist(this);
    }

    public MutableDd dd() {
        return new MutableDd(this);
    }

    public MutableDel del() {
        return new MutableDel(this);
    }

    public MutableDfn dfn() {
        return new MutableDfn(this);
    }

    public MutableDiv div() {
        return new MutableDiv(this);
    }

    public MutableDl dl() {
        return new MutableDl(this);
    }

    public MutableDt dt() {
        return new MutableDt(this);
    }

    public MutableEm em() {
        return new MutableEm(this);
    }

    public MutableEmbed embed() {
        return new MutableEmbed(this);
    }

    public MutableFieldset fieldset() {
        return new MutableFieldset(this);
    }

    public MutableFigcaption figcaption() {
        return new MutableFigcaption(this);
    }

    public MutableFigure figure() {
        return new MutableFigure(this);
    }

    public MutableFooter footer() {
        return new MutableFooter(this);
    }

    public MutableForm form() {
        return new MutableForm(this);
    }

    public MutableH1 h1() {
        return new MutableH1(this);
    }

    public MutableH2 h2() {
        return new MutableH2(this);
    }

    public MutableH3 h3() {
        return new MutableH3(this);
    }

    public MutableH4 h4() {
        return new MutableH4(this);
    }

    public MutableH5 h5() {
        return new MutableH5(this);
    }

    public MutableH6 h6() {
        return new MutableH6(this);
    }

    public MutableHead head() {
        return new MutableHead(this);
    }

    public MutableHeader header() {
        return new MutableHeader(this);
    }

    public MutableHr hr() {
        return new MutableHr(this);
    }

    public MutableHtml html() {
        return new MutableHtml(this);
    }

    public MutableI i() {
        return new MutableI(this);
    }

    public MutableIframe iframe() {
        return new MutableIframe(this);
    }

    public MutableImg img() {
        return new MutableImg(this);
    }

    public MutableInput input() {
        return new MutableInput(this);
    }

    public MutableIns ins() {
        return new MutableIns(this);
    }

    public MutableKbd kbd() {
        return new MutableKbd(this);
    }

    public MutableKeygen keygen() {
        return new MutableKeygen(this);
    }

    public MutableLabel label() {
        return new MutableLabel(this);
    }

    public MutableLegend legend() {
        return new MutableLegend(this);
    }

    public MutableLi li() {
        return new MutableLi(this);
    }

    public MutableLink link() {
        return new MutableLink(this);
    }

    public MutableMain main() {
        return new MutableMain(this);
    }

    public MutableMap map() {
        return new MutableMap(this);
    }

    public MutableMark mark() {
        return new MutableMark(this);
    }

    public MutableMeta meta() {
        return new MutableMeta(this);
    }

    public MutableMeter meter() {
        return new MutableMeter(this);
    }

    public MutableNav nav() {
        return new MutableNav(this);
    }

    public MutableNoscript noscript() {
        return new MutableNoscript(this);
    }

    public MutableObject object() {
        return new MutableObject(this);
    }

    public MutableOl ol() {
        return new MutableOl(this);
    }

    public MutableOptgroup optgroup() {
        return new MutableOptgroup(this);
    }

    public MutableOption option() {
        return new MutableOption(this);
    }

    public MutableOutput output() {
        return new MutableOutput(this);
    }

    public MutableP p() {
        return new MutableP(this);
    }

    public MutableParam param() {
        return new MutableParam(this);
    }

    public MutablePre pre() {
        return new MutablePre(this);
    }

    public MutableProgress progress() {
        return new MutableProgress(this);
    }

    public MutableQ q() {
        return new MutableQ(this);
    }

    public MutableRb rb() {
        return new MutableRb(this);
    }

    public MutableRp rp() {
        return new MutableRp(this);
    }

    public MutableRt rt() {
        return new MutableRt(this);
    }

    public MutableRtc rtc() {
        return new MutableRtc(this);
    }

    public MutableRuby ruby() {
        return new MutableRuby(this);
    }

    public MutableS s() {
        return new MutableS(this);
    }

    public MutableSamp samp() {
        return new MutableSamp(this);
    }

    public MutableScript script() {
        return new MutableScript(this);
    }

    public MutableSection section() {
        return new MutableSection(this);
    }

    public MutableSelect select() {
        return new MutableSelect(this);
    }

    public MutableSmall small() {
        return new MutableSmall(this);
    }

    public MutableSource source() {
        return new MutableSource(this);
    }

    public MutableSpan span() {
        return new MutableSpan(this);
    }

    public MutableStrong strong() {
        return new MutableStrong(this);
    }

    public MutableStyle style() {
        return new MutableStyle(this);
    }

    public MutableSubandsup subandsup() {
        return new MutableSubandsup(this);
    }

    public MutableTable table() {
        return new MutableTable(this);
    }

    public MutableTbody tbody() {
        return new MutableTbody(this);
    }

    public MutableTd td() {
        return new MutableTd(this);
    }

    public MutableTemplate template() {
        return new MutableTemplate(this);
    }

    public MutableTextarea textarea() {
        return new MutableTextarea(this);
    }

    public MutableTfoot tfoot() {
        return new MutableTfoot(this);
    }

    public MutableTh th() {
        return new MutableTh(this);
    }

    public MutableThead thead() {
        return new MutableThead(this);
    }

    public MutableTime time() {
        return new MutableTime(this);
    }

    public MutableTitle title() {
        return new MutableTitle(this);
    }

    public MutableTr tr() {
        return new MutableTr(this);
    }

    public MutableTrack track() {
        return new MutableTrack(this);
    }

    public MutableU u() {
        return new MutableU(this);
    }

    public MutableUl ul() {
        return new MutableUl(this);
    }

    public MutableVar var() {
        return new MutableVar(this);
    }

    public MutableVideo video() {
        return new MutableVideo(this);
    }

    public MutableWbr wbr() {
        return new MutableWbr(this);
    }

    @Override
    public MutableDetails details() {
        return new MutableDetails(this);
    }

    @Override
    public MutableDialog dialog() {
        return new MutableDialog(this);
    }

    @Override
    public MutableSummary summary() {
        return new MutableSummary(this);
    }

}
