package net.bodz.xml.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import net.bodz.bas.io.LAReader;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.PrefetchedIterator;

/**
 * @test {@link TermParserTest}
 */
public class TermParser {

    private final TermDict dict;

    public TermParser() {
        this(null);
    }

    /**
     * @param dict
     *            used to convert name to id-integer.
     */
    public TermParser(TermDict dict) {
        this.dict = dict;
    }

    public Iterable<Term> parse(final String text) {
        if (text == null)
            throw new NullPointerException("text");
        return new Iterable<Term>() {
            @Override
            public Iterator<Term> iterator() {
                return new Iter(new StringReader(text));
            }
        };
    }

    public Iterator<Term> parse(Reader reader) {
        if (reader == null)
            throw new NullPointerException("reader");
        return new Iter(reader);
    }

    public static Iterable<Term> parse(TermDict dict, String text) {
        return new TermParser(dict).parse(text);
    }

    class Iter extends PrefetchedIterator<Term> {

        final LAReader    reader;
        StringBuffer      buffer;
        ArrayList<String> parambuf;

        public Iter(Reader reader) {
            this.reader = new LAReader(reader);
            buffer = new StringBuffer();
            parambuf = new ArrayList<String>();
        }

        int skip() throws IOException {
            int look;
            while (true) {
                look = reader.look();
                if (!Character.isSpaceChar(look))
                    return look;
                reader.read();
            }
        }

        static final int EOF    = -1; // \w[a-z0-9_]*+
        static final int BUFFER = -2;

        int readToken() throws IOException {
            while (true) {
                int c = reader.read();
                if (c == -1)
                    return EOF;
                if (Character.isSpaceChar((c)))
                    continue;
                if (!Character.isLetter(c)) // non-letter
                    return c;
                buffer.setLength(0);
                buffer.append((char) c);
                break;
            }
            while (true) {
                int look = reader.look();
                if (look == -1)
                    break;
                if (Character.isLowerCase(look))
                    buffer.append((char) reader.read());
                else
                    break;
            }
            return BUFFER;
        }

        String readTill(String stops) throws IOException {
            int look = reader.look();
            if (look == -1)
                return null;
            buffer.setLength(0);
            do {
                if (stops.indexOf(look) != -1)
                    break;
                buffer.append((char) look);
                reader.read();
                look = reader.look();
            } while (look != -1);
            return buffer.toString();
        }

        String readArg(String stopsAndSeparator) throws IOException {
            String s = readTill(stopsAndSeparator);
            if (s != null)
                s = s.trim();
            return s;
        }

        String[] readArgs(char separator, String stops) throws IOException, ParseException {
            String stopsAndSeparator = separator + stops;
            String arg = readArg(stopsAndSeparator);
            if (arg == null)
                return null;
            int look = reader.look();
            if (arg.isEmpty())
                if (look != separator)
                    return new String[0];

            parambuf.clear();
            parambuf.add(arg);
            while (look != -1) {
                if (stops.indexOf(look) != -1)
                    break;
                reader.read(); // skip separator
                arg = readArg(stopsAndSeparator);
                if (arg == null)
                    throw new ParseException("Unexpected EOF");
                parambuf.add(arg);
                look = reader.look();
            }
            return parambuf.toArray(new String[0]);
        }

        Term readTerm() throws ParseException, IOException {
            String name;
            int token = readToken();
            switch (token) {
            case -1:
                return null;
            case BUFFER:
                name = buffer.toString();
                break;
            default:
                throw new ParseException("expect for name, but encountered '" + (char) token + "'");
            }
            int id = 0;
            if (dict != null) {
                Integer _id = dict.getId(name);
                if (_id != null)
                    id = _id;
            }
            Term term = new Term(id, name);

            int look = skip();
            if (Character.isDigit(look)) {
                int n = 0;
                while (Character.isDigit(look)) {
                    n = n * 10 + look - '0';
                    reader.read();
                    look = reader.look();
                }
                term.setIndex(n);
            }
            if (look == '<' || look == '\u00AB') { // «
                reader.read();
                String typeParameter = readArg(look == '<' ? ">" : "\u00BB"); // »
                term.setTypeParameter(typeParameter);
                reader.read();
                look = skip();
            }
            if (look == '(') {
                reader.read();
                String[] parameters = readArgs(',', ")");
                term.setParameters(parameters);
                reader.read();
                look = skip();
            }
            if (look == '[') {
                reader.read();
                String[] bounds = readArgs(',', "]");
                term.setBounds(bounds);
                reader.read();
                // look=skip();
            }
            return term;
        }

        @Override
        protected Term fetch() {
            try {
                Term term = readTerm();
                return term == null ? end() : term;
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

    }

}
