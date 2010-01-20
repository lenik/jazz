package net.bodz.bas.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.exceptions.IllegalUsageException;
import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.text.util.StringSearch;
import net.bodz.bas.text.util.Strings;

/**
 * @test {@link ConsoleInteractionTest}
 */
public class ConsoleUI
        extends AbstractUserInterface {

    private LineReader lineIn;
    private PrintStream out;

    public ConsoleUI(InputStream in, PrintStream out) {
        if (in == null)
            throw new NullPointerException("in");
        if (out == null)
            throw new NullPointerException("out");
        InputStreamReader isr = new InputStreamReader(System.in);
        lineIn = new LineReader(isr);
        this.out = out;
    }

    public static final ConsoleUI stdout;
    public static final ConsoleUI stderr;
    static {
        stdout = new ConsoleUI(System.in, System.out);
        stderr = new ConsoleUI(System.in, System.err);
    }

    void print(String title, Object detail) {
        out.print(title);
        if (detail != null)
            out.print(": \n    " + detail);
        if (detail instanceof Throwable) {
            ((Throwable) detail).printStackTrace(out);
        }
        out.println();
    }

    @Override
    public void alert(String title, Object detail) {
        print(title, detail);
        out.println("(press enter to continue)");
        try {
            lineIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean confirm(String title, Object detail) {
        print(title, detail);
        out.println("Confirm (y/n)?");
        try {
            String line = lineIn.readLine();
            if (line == null)
                return false;
            line = line.trim();
            if ("y".equals(line) || "yes".equals(line))
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static String getDisplayName(IProposal p) {
        char c = p.getMnemonic();
        String name = p.getName();
        String s = Strings.ucfirstWords(name);
        int occur = StringSearch.indexOfIgnoreCase(s, c);
        if (occur != -1) {
            s = s.substring(0, occur) + '(' + s.substring(occur, occur + 1) + ')' + s.substring(occur + 1);
        }
        return s;
    }

    @Override
    public int ask(String title, Object detail, IProposal... proposals) {
        if (proposals == null)
            throw new NullPointerException("proposals");
        if (proposals.length == 0)
            throw new IllegalArgumentException("No proposal");
        print(title, detail);
        do {
            out.print("Choose a proposal: ");
            for (int i = 0; i < proposals.length; i++) {
                IProposal p = proposals[i];
                String name = getDisplayName(p);
                // String desc = p.getDescription();
                // String s = desc == null ? name : name + ": " + desc;
                if (i != 0)
                    out.print(", ");
                out.print(name);
            }
            out.print(": ");
            String line;
            try {
                line = lineIn.readLine();
            } catch (IOException e) {
                throw new UIRuntimeException(e);
            }
            if (line == null)
                return 0;
            char ch = line.isEmpty() ? '\0' : line.charAt(0);
            int mostNearly = -1;
            for (int i = 0; i < proposals.length; i++) {
                IProposal p = proposals[i];
                if (line.equals(p.getName()))
                    return i;
                if (line.equalsIgnoreCase(p.getName()))
                    mostNearly = i;
                if (ch != '\0' && ch == p.getMnemonic())
                    mostNearly = i;
            }
            if (mostNearly != -1)
                return mostNearly;
            System.err.println("Bad selection: " + line);
        } while (true);
    }

    @Override
    public <T> T prompt(String title, Object detail, Class<T> type, T initial) {
        print(title, detail);
        out.println("Enter: (default: " + initial + ")");
        for (int ntry = 0; ntry < 3; ntry++)
            try {
                String line = lineIn.readLine();
                if (line == null)
                    return initial;
                T val = TypeParsers.parse(type, line);
                return val;
            } catch (ParseException e) {
                System.err.println("Parse Error: " + e.getMessage());
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        System.err.println("Too many errors. ");
        return null;
    }

    @Override
    public <K> K choice(String title, Object detail, Map<K, ?> candidates, K initial) {
        if (candidates.isEmpty())
            throw new IllegalUsageException("Nothing to choice");
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) candidates.keySet().toArray();
        Map<String, K> keynames = new HashMap<String, K>();
        Set<String> dupkeys = new HashSet<String>();
        while (true) {
            print(title, detail);
            out.println("Candidates: ");
            int i = 0;
            for (K key : candidates.keySet()) {
                String keyname = String.valueOf(key);
                if (keynames.containsKey(keyname))
                    dupkeys.add(keyname);
                else
                    keynames.put(keyname, key);
                keys[i++] = key;
                // 1-based for display.
                Object value = candidates.get(key);
                out.println("  " + i + ". " + keyname + " => " + value);
            }
            out.println(String.format("Enter your choice(1..%d, or name): \n", keys.length));
            try {
                String line = lineIn.readLine();
                if (line == null)
                    return initial;
                line = line.trim();
                try {
                    int index = Integer.parseInt(line) - 1;
                    if (index >= 0 && index < keys.length)
                        return keys[index];
                } catch (NumberFormatException ex) {
                }
                if (dupkeys.contains(line))
                    System.err.println(//
                            "Duplicated entry, please choose by index instead. ");
                if (keynames.containsKey(line))
                    return keynames.get(line);
                System.err.println("Bad choice, try again. ");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public <K> Set<K> choices(String title, Object detail, Map<K, ?> candidates, K... initial) {
        throw new NotImplementedException("multiple choice");
    }

}
