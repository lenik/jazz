package net.bodz.bas.text.diff.gnudiff;

import java.io.File;
import java.util.List;

import net.bodz.bas.c1.util.Dates;
import net.bodz.bas.closure.alt.Pred1;
import net.bodz.bas.sio.CharOut;
import net.bodz.bas.text.diff.DiffInfo;

/**
 * A simple framework for printing change lists produced by <code>Diff</code>.
 * 
 * @see _GNUDiff.util.GNUDiff
 * @author Stuart D. Gathman Copyright (C) 2000 Business Management Systems, Inc.
 *         <p>
 *         This program is free software; you can redistribute it and/or modify it under the terms
 *         of the GNU General Public License as published by the Free Software Foundation; either
 *         version 1, or (at your option) any later version.
 *         <p>
 *         This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *         without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *         See the GNU General Public License for more details.
 *         <p>
 *         You should have received a copy of the GNU General Public License along with this
 *         program; if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA
 *         02139, USA.
 */
public class DiffPrint {
    /**
     * A Base class for printing edit scripts produced by Diff. This class divides the change list
     * into "hunks", and calls <code>print_hunk</code> for each hunk. Various utility methods are
     * provided as well.
     */
    public static abstract class Base {
        protected CharOut outfile = CharOut.stdout;

        protected Base(List<?> a, List<?> b, CharOut outfile) {
            file0 = a;
            file1 = b;
        }

        /**
         * Set to ignore certain kinds of lines when printing an edit script. For example, ignoring
         * blank lines or comments.
         */
        protected Pred1<Object> ignore = null;

        /**
         * Set to the lines of the files being compared.
         */
        protected List<?> file0, file1;

        /**
         * Divide SCRIPT into pieces by calling HUNKFUN and print each piece with PRINTFUN. Both
         * functions take one arg, an edit script.
         * 
         * PRINTFUN takes a subscript which belongs together (with a null link at the end) and
         * prints it.
         */
        public void print_script(List<DiffInfo> script) {
            int i = 0;
            while (i < script.size()) {
                int end = hunkfun(script, i);
                print_hunk(script, i, end);
            }
        }

        /**
         * Called with the tail of the script and returns the last link that belongs together with
         * the start of the tail.
         */

        protected int hunkfun(List<DiffInfo> changes, int start) {
            return start + 1;
        }

        protected int first0, last0, first1, last1, deletes, inserts;

        /**
         * Look at a hunk of edit script and report the range of lines in each file that it applies
         * to. HUNK is the start of the hunk, which is a chain of `struct change'. The first and
         * last line numbers of file 0 are stored in *FIRST0 and *LAST0, and likewise for file 1 in
         * *FIRST1 and *LAST1. Note that these are internal line numbers that count from 0.
         * 
         * If no lines from file 0 are deleted, then FIRST0 is LAST0+1.
         * 
         * Also set *DELETES nonzero if any lines of file 0 are deleted and set *INSERTS nonzero if
         * any lines of file 1 are inserted. If only ignorable lines are inserted or deleted, both
         * are set to 0.
         */

        protected void analyze_hunk(List<DiffInfo> list, int start, int end) {
            int f0, l0 = 0, f1, l1 = 0, show_from = 0, show_to = 0;
            int i;
            boolean nontrivial = (ignore == null);

            show_from = show_to = 0;

            DiffInfo hunk = list.get(start);
            f0 = hunk.index0;
            f1 = hunk.index1;

            while (start < end) {
                DiffInfo next = list.get(start);
                l0 = next.index0 + next.deleted - 1;
                l1 = next.index1 + next.inserted - 1;
                show_from += next.deleted;
                show_to += next.inserted;
                for (i = next.index0; i <= l0 && !nontrivial; i++)
                    if (!ignore.eval(file0.get(i)))
                        nontrivial = true;
                for (i = next.index1; i <= l1 && !nontrivial; i++)
                    if (!ignore.eval(file1.get(i)))
                        nontrivial = true;
                start++;
            }

            first0 = f0;
            last0 = l0;
            first1 = f1;
            last1 = l1;

            /*
             * If all inserted or deleted lines are ignorable, tell the caller to ignore this hunk.
             */

            if (!nontrivial)
                show_from = show_to = 0;

            deletes = show_from;
            inserts = show_to;
        }

        /** Print the script header which identifies the files compared. */
        protected void print_header(String filea, String fileb) {
        }

        protected abstract void print_hunk(List<DiffInfo> list, int start, int end);

        protected void print_1_line(String pre, Object linbuf) {
            outfile.println(pre + linbuf.toString());
        }

        /**
         * Print a pair of line numbers with SEPCHAR, translated for file FILE. If the two numbers
         * are identical, print just one number.
         * 
         * Args A and B are internal line numbers. We print the translated (real) line numbers.
         */

        protected void print_number_range(char sepchar, int a, int b) {
            /*
             * Note: we can have B < A in the case of a range of no lines. In this case, we should
             * print the line number before the range, which is B.
             */
            if (++b > ++a)
                outfile.print("" + a + sepchar + b);
            else
                outfile.print(b);
        }

        public static char change_letter(int inserts, int deletes) {
            if (inserts == 0)
                return 'd';
            else if (deletes == 0)
                return 'a';
            else
                return 'c';
        }
    }

    /**
     * Print a change list in the standard diff format.
     */
    public static class NormalPrint
            extends Base {

        public NormalPrint(List<?> a, List<?> b, CharOut out) {
            super(a, b, out);
        }

        /**
         * Print a hunk of a normal diff. This is a contiguous portion of a complete edit script,
         * describing changes in consecutive lines.
         */

        @Override
        protected void print_hunk(List<DiffInfo> list, int start, int end) {
            /* Determine range of line numbers involved in each file. */
            analyze_hunk(list, start, end);
            if (deletes == 0 && inserts == 0)
                return;

            /* Print out the line number header for this hunk */
            print_number_range(',', first0, last0);
            outfile.print(change_letter(inserts, deletes));
            print_number_range(',', first1, last1);
            outfile.println();

            /* Print the lines that the first file has. */
            if (deletes != 0)
                for (int i = first0; i <= last0; i++)
                    print_1_line("< ", file0.get(i));

            if (inserts != 0 && deletes != 0)
                outfile.println("---");

            /* Print the lines that the second file has. */
            if (inserts != 0)
                for (int i = first1; i <= last1; i++)
                    print_1_line("> ", file1.get(i));
        }
    }

    /**
     * Prints an edit script in a format suitable for input to <code>ed</code>. The edit script must
     * be generated with the reverse option to be useful as actual <code>ed</code> input.
     */
    public static class EdPrint
            extends Base {

        public EdPrint(List<?> a, List<?> b, CharOut out) {
            super(a, b, out);
        }

        /** Print a hunk of an ed diff */
        @Override
        protected void print_hunk(List<DiffInfo> list, int start, int end) {

            /* Determine range of line numbers involved in each file. */
            analyze_hunk(list, start, end);
            if (deletes == 0 && inserts == 0)
                return;

            /* Print out the line number header for this hunk */
            print_number_range(',', first0, last0);
            outfile.println(change_letter(inserts, deletes));

            /* Print new/changed lines from second file, if needed */
            if (inserts != 0) {
                boolean inserting = true;
                for (int i = first1; i <= last1; i++) {
                    /* Resume the insert, if we stopped. */
                    if (!inserting)
                        outfile.println(i - first1 + first0 + "a");
                    inserting = true;

                    /*
                     * If the file's line is just a dot, it would confuse `ed'. So output it with a
                     * double dot, and set the flag LEADING_DOT so that we will output another
                     * ed-command later to change the double dot into a single dot.
                     */

                    if (".".equals(file1.get(i))) {
                        outfile.println("..");
                        outfile.println(".");
                        /* Now change that double dot to the desired single dot. */
                        outfile.println(i - first1 + first0 + 1 + "s/^\\.\\././");
                        inserting = false;
                    } else
                        /* Line is not `.', so output it unmodified. */
                        print_1_line("", file1.get(i));
                }

                /* End insert mode, if we are still in it. */
                if (inserting)
                    outfile.println(".");
            }
        }
    }

    /**
     * Prints an edit script in context diff format. This and its 'unified' variation is used for
     * source code patches.
     */
    public static class ContextPrint
            extends Base {

        protected int context = 3;

        public ContextPrint(List<?> a, List<?> b, CharOut out) {
            super(a, b, out);
        }

        protected void print_context_label(String mark, File inf, String label) {
            if (label != null)
                outfile.println(mark + ' ' + label);
            else if (inf.lastModified() > 0)
                outfile.println(mark + ' ' + inf.getPath() + '\t' + Dates.sysDateTimeFormat.format(inf.lastModified()));
            else
                /* Don't pretend that standard input is ancient. */
                outfile.println(mark + ' ' + inf.getPath());
        }

        @Override
        public void print_header(String filea, String fileb) {
            print_context_label("***", new File(filea), filea);
            print_context_label("---", new File(fileb), fileb);
        }

        /** If function_regexp defined, search for start of function. */
        private String find_function(List<?> lines, int start) {
            return null;
        }

        protected void print_function(List<?> file, int start) {
            String function = find_function(file0, first0);
            if (function != null) {
                outfile.print(" ");
                outfile.print((function.length() < 40) ? function : function.substring(0, 40));
            }
        }

        @Override
        protected void print_hunk(List<DiffInfo> list, int start, int end) {

            /* Determine range of line numbers involved in each file. */

            analyze_hunk(list, start, end);

            if (deletes == 0 && inserts == 0)
                return;

            /* Include a context's width before and after. */

            first0 = Math.max(first0 - context, 0);
            first1 = Math.max(first1 - context, 0);
            last0 = Math.min(last0 + context, file0.size() - 1);
            last1 = Math.min(last1 + context, file1.size() - 1);

            outfile.print("***************");

            /*
             * If we looked for and found a function this is part of, include its name in the header
             * of the diff section.
             */
            print_function(file0, first0);

            outfile.println();
            outfile.print("*** ");
            print_number_range(',', first0, last0);
            outfile.println(" ****");

            if (deletes != 0) {
                int off = start;

                for (int i = first0; i <= last0; i++) {
                    /*
                     * Skip past changes that apply (in file 0) only to lines before line I.
                     */

                    DiffInfo next = list.get(off);
                    while (off < end && next.index0 + next.deleted <= i)
                        next = list.get(++off);

                    /* Compute the marking for line I. */

                    String prefix = " ";
                    if (next != null && next.index0 <= i)
                        /*
                         * The change NEXT covers this line. If lines were inserted here in file 1,
                         * this is "changed". Otherwise it is "deleted".
                         */
                        prefix = (next.inserted > 0) ? "!" : "-";

                    print_1_line(prefix, file0.get(i));
                }
            }

            outfile.print("--- ");
            print_number_range(',', first1, last1);
            outfile.println(" ----");

            if (inserts != 0) {
                int off = start;

                for (int i = first1; i <= last1; i++) {
                    /*
                     * Skip past changes that apply (in file 1) only to lines before line I.
                     */

                    DiffInfo next = list.get(off);
                    while (off < end && next.index1 + next.inserted <= i)
                        next = list.get(++off);

                    /* Compute the marking for line I. */

                    String prefix = " ";
                    if (off < end && next.index1 <= i)
                        /*
                         * The change NEXT covers this line. If lines were deleted here in file 0,
                         * this is "changed". Otherwise it is "inserted".
                         */
                        prefix = (next.deleted > 0) ? "!" : "+";

                    print_1_line(prefix, file1.get(i));
                }
            }
        }
    }

    /**
     * Prints an edit script in context diff format. This and its 'unified' variation is used for
     * source code patches.
     */
    public static class UnifiedPrint
            extends ContextPrint {

        public UnifiedPrint(List<?> a, List<?> b, CharOut out) {
            super(a, b, out);
        }

        @Override
        public void print_header(String filea, String fileb) {
            print_context_label("---", new File(filea), filea);
            print_context_label("+++", new File(fileb), fileb);
        }

        private void print_number_range(int a, int b) {
            // translate_range (file, a, b, &trans_a, &trans_b);

            /*
             * Note: we can have B < A in the case of a range of no lines. In this case, we should
             * print the line number before the range, which is B.
             */
            if (b < a)
                outfile.print(b + ",0");
            else
                super.print_number_range(',', a, b);
        }

        @Override
        protected void print_hunk(List<DiffInfo> list, int start, int end) {
            /* Determine range of line numbers involved in each file. */
            analyze_hunk(list, start, end);

            if (deletes == 0 && inserts == 0)
                return;

            /* Include a context's width before and after. */

            first0 = Math.max(first0 - context, 0);
            first1 = Math.max(first1 - context, 0);
            last0 = Math.min(last0 + context, file0.size() - 1);
            last1 = Math.min(last1 + context, file1.size() - 1);

            outfile.print("@@ -");
            print_number_range(first0, last0);
            outfile.print(" +");
            print_number_range(first1, last1);
            outfile.print(" @@");

            /*
             * If we looked for and found a function this is part of, include its name in the header
             * of the diff section.
             */
            print_function(file0, first0);

            outfile.println();

            int i = first0;
            int j = first1;

            while (i <= last0 || j <= last1) {

                /*
                 * If the line isn't a difference, output the context from file 0.
                 */

                if (start >= end || i < list.get(start).index0) {
                    if (i < file0.size()) {
                        outfile.print(' ');
                        print_1_line("", file0.get(i++));
                    }
                    j++;
                } else {
                    /* For each difference, first output the deleted part. */

                    DiffInfo next = list.get(start++);
                    int k = next.deleted;
                    while (k-- > 0) {
                        outfile.print('-');
                        print_1_line("", file0.get(i++));
                    }

                    /* Then output the inserted part. */

                    k = next.inserted;
                    while (k-- > 0) {
                        outfile.print('+');
                        print_1_line("", file1.get(j++));
                    }

                    /* We're done with this hunk, so on to the next! */
                }
            }
        }
    }

}
