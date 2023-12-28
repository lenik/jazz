package net.bodz.bas.html.util;


import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.builtin.URLResource;

public class JsoupFn {

    public static Document parseXml(URL url)
            throws IOException, ParseException {
        URLResource res = new URLResource(url);
        String xml = res.read().readString();
        return parseXml(xml);
    }

    public static Document parseXml(String xml)
            throws ParseException {
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        return doc;

    }

}
