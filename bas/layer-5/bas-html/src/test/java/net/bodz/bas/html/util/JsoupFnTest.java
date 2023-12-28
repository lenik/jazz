package net.bodz.bas.html.util;

import java.io.IOException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class JsoupFnTest
        extends Assert {

    @Test
    public void testParseXml()
            throws IOException, ParseException {
        URL url = JsoupFnTest.class.getResource("lib.xml");
        Document doc = JsoupFn.parseXml(url);
        System.out.println(doc);
    }

    public static void main(String[] args)
            throws Exception {
        URL url = JsoupFnTest.class.getResource("lib.xml");
        Document doc = JsoupFn.parseXml(url);
        Elements list = doc.select("actor");
        System.out.println("size of list: " + list.size());
        for (Element item : list) {
            System.out.printf(" > [%s] %s\n", item.tagName(), item.text());
        }
    }

}
