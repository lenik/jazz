package net.bodz.xml.extb;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import net.bodz.bas.test.types.Person;

public class ExtbHandlerTest {

    /**
     * <pre>
     * &lt;boy|girl name=&quot;NAME&quot; age=&quot;AGE&quot;&gt;
     *     &lt;address&gt;
     *         &lt;country /&gt;
     *         &lt;city /&gt;
     *         &lt;address /&gt;
     *         &lt;post-code /&gt;
     *         &lt;phone /&gt;
     *     &lt;/address&gt;
     * &lt;/boy&gt;
     * </pre>
     */
    static class PersonXB extends ExtbHandler {

        private final Person person;

        public PersonXB(ExtbParser parser, Person person) {
            super(parser);
            this.person = person;
        }

        @Override
        protected ExtbHandler startTag(String uri, String name,
                Attributes attributes) {
            if ("boy".equals(name))
                person.setGirl(false);
            else if ("girl".equals(name))
                person.setGirl(true);
            else
                parser.reportError(ExtbParser.ERROR, "Invalid tag: " + name);
            attributes.get
            return this;
        }

    }

}
