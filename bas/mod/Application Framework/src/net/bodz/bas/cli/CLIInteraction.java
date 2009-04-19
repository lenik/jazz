package net.bodz.bas.cli;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.io.LineReader;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.util._Interaction;

public class CLIInteraction extends _Interaction {

    static LineReader lineIn;
    static {
        InputStreamReader isr = new InputStreamReader(System.in);
        lineIn = new LineReader(isr);
    }

    void print(String title, Object detail) {
        System.out.print(title);
        if (detail != null)
            System.out.print(": \n    " + detail); //$NON-NLS-1$
        System.out.println();
    }

    @Override
    public void alert(String title, Object detail) {
        print(title, detail);
        System.out.println(AppNLS.getString("CLIInteraction.pressEnter")); //$NON-NLS-1$
        try {
            lineIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean confirm(String title, Object detail) {
        print(title, detail);
        System.out.println(AppNLS.getString("CLIInteraction.confirm")); //$NON-NLS-1$
        try {
            String line = lineIn.readLine();
            if (line == null)
                return false;
            line = line.trim();
            if ("y".equals(line) || "yes".equals(line)) //$NON-NLS-1$ //$NON-NLS-2$
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T> T prompt(String title, Object detail, Class<T> type, T initial) {
        print(title, detail);
        System.out
                .println(AppNLS.getString("CLIInteraction.enterDefault_") + initial + ")"); //$NON-NLS-1$ //$NON-NLS-2$
        for (int ntry = 0; ntry < 3; ntry++) {
            try {
                String line = lineIn.readLine();
                if (line == null)
                    return initial;
                T val = TypeParsers.parse(type, line);
                return val;
            } catch (ParseException e) {
                System.err
                        .println(AppNLS.getString("CLIInteraction.parseError") + e.getMessage()); //$NON-NLS-1$
                continue;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.err.println(AppNLS.getString("CLIInteraction.tooManyError")); //$NON-NLS-1$
        return null;
    }

    @Override
    public <K> K choice(String title, Object detail, Map<K, ?> candidates,
            K initial) {
        if (candidates.isEmpty())
            throw new IllegalUsageException(AppNLS
                    .getString("CLIInteraction.nothingToChoice")); //$NON-NLS-1$
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) candidates.keySet().toArray();
        TextMap<K> keynames = new HashTextMap<K>();
        Set<String> dupkeys = new HashSet<String>();
        while (true) {
            print(title, detail);
            System.out.println(AppNLS.getString("CLIInteraction.candidates_")); //$NON-NLS-1$
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
                System.out.println("  " + i + ". " + keyname + " => " + value); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            System.out.println(String.format(
                    AppNLS.getString("CLIInteraction.enterYourChoice_d"), keys.length)); //$NON-NLS-1$
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
                if (dupkeys.contains(line)) {
                    System.err.println(//
                            AppNLS.getString("CLIInteraction.dupEntry")); //$NON-NLS-1$
                }
                if (keynames.containsKey(line))
                    return keynames.get(line);
                System.err
                        .println(AppNLS.getString("CLIInteraction.badChoice")); //$NON-NLS-1$
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public <K> Set<K> choices(String title, Object detail,
            Map<K, ?> candidates, K... initial) {
        throw new NotImplementedException("multiple choice"); //$NON-NLS-1$
    }

}
