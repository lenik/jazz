package net.bodz.bas.io.html;

import net.bodz.bas.gui.css3.property.DirectionMode;
import net.bodz.bas.io.xml.IXmlTagBuilder;

public abstract class AbstractHtmlTagBuilder<self_t extends IXmlTagBuilder>
        implements IHtmlTagBuilder<self_t> {

    @Override
    public self_t id(String id) {
        return attr("id", id);
    }

    @Override
    public final self_t text(Object content) {
        return text(content == null ? null : content.toString());
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

}
