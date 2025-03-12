package net.bodz.bas.doc.node.conv;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.doc.node.AbstractDocVisitor;
import net.bodz.bas.doc.node.Document;
import net.bodz.bas.doc.node.FontEnv;
import net.bodz.bas.doc.node.FontStyleEnv;
import net.bodz.bas.doc.node.Hr;
import net.bodz.bas.doc.node.Image;
import net.bodz.bas.doc.node.ListItem;
import net.bodz.bas.doc.node.ListPar;
import net.bodz.bas.doc.node.Part;
import net.bodz.bas.doc.node.PartGroup;
import net.bodz.bas.doc.node.RunGroup;
import net.bodz.bas.doc.node.Table;
import net.bodz.bas.doc.node.TableCell;
import net.bodz.bas.doc.node.TableRow;
import net.bodz.bas.doc.node.TextBox;
import net.bodz.bas.doc.node.TextPar;
import net.bodz.bas.doc.node.TextRun;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.stack.ContextStack;

public class LaTeXConverter
        extends AbstractDocVisitor {

    ITreeOut out;

    ContextStack<TextParHandler> textParHandlerStack = new ContextStack<>();

    String cjkMainFont = "AR PL UMing CN";
    String cjkSansFont = "AR PL UMing CN";

    boolean setParskipToBaselineskip = true;
    boolean newPageForChapter = false;
    boolean newPageForSection = false;
    boolean newPageForSubsection = false;
    boolean newPageForSubsubsection = false;

    boolean tableOfContents = true;
    int fontSize = 13;
    int baseLineStrech = 30;
    boolean appendix = true;

    public LaTeXConverter(ITreeOut out) {
        this.out = out;
    }

    String mlEscape(String text) {
        String mlText = text.replace("\n", "\\\\");
        return mlText;
    }

    String escapeLatex(String s) {
        s = s.replace("\\", "\\\\");
        s = s.replace("&", "\\&");
        s = s.replace("%", "\\%");
        s = s.replace("$", "\\$");
        s = s.replace("#", "\\#");
        s = s.replace("_", "\\_");
        s = s.replace("{", "\\{");
        s = s.replace("}", "\\}");
        s = s.replace("~", "\\~");
        s = s.replace("^", "\\^");
        return s;
    }

    @Override
    public void document(Document doc) {
        out.println("\\documentclass[CJK, 12pt, hyperref, oneside]{z-article}");
        out.println("\\usepackage[vmargin=3cm]{geometry}");
        out.println("\\usepackage[bookmarksopen=true]{hyperref}");
        out.println("    \\hypersetup{pdfborder = {0 0 0}}");
        out.println("\\usepackage[open,openlevel=1]{bookmark}");
        out.println("\\usepackage{environ}");
        out.println("\\usepackage{fancyvrb}");
        out.println("\\usepackage{float}");
        out.println("\\usepackage{fontawesome}");
        out.println("\\usepackage{listings}");
        out.println("\\usepackage{longtable,booktabs}");
        out.println("\\usepackage{lscape}");
        out.println("\\usepackage{keystroke}");
        out.println("\\usepackage{mdframed}");
        out.println("\\usepackage{pdflscape}");
        out.println("\\usepackage[normalem]{ulem}");
        out.println("\\usepackage{verbatim}");
        out.println("\\usepackage{wrapfig}");

        if (newPageForChapter)
            out.println("\\pretocmd{\\chapter}{\\clearpage}{}{}");
        if (newPageForSection)
            out.println("\\pretocmd{\\section}{\\clearpage}{}{}");
        if (newPageForSubsection)
            out.println("\\pretocmd{\\subsection}{\\clearpage}{}{}");
        if (newPageForSubsubsection)
            out.println("\\pretocmd{\\subsubsection}{\\clearpage}{}{}");

        out.println("\\usepackage{svg}");
        out.println("\\usepackage{stmaryrd}");

        if (setParskipToBaselineskip) {
            out.println("");
            out.println("\\setlength{\\parskip}{\\baselineskip}");
        }

        out.println("");
        if (cjkMainFont != null)
            out.println("\\setCJKmainfont{" + cjkMainFont + "}");
        if (cjkSansFont != null)
            out.println("\\setCJKsansfont{" + cjkSansFont + "}");

        String title = doc.title.getText();
        String author = doc.author.getText();

        out.println("");
        if (!Nullables.isEmpty(title)) {
            String mlTitle = mlEscape(title);
            out.printf("\\title { %s }", mlTitle);
        }
        if (!Nullables.isEmpty(author)) {
            String mlAuthor = mlEscape(author);
            out.println("\\author{ %s }", mlAuthor);
        }

        out.println("");
        out.println("\\begin{document}");

        if (!Nullables.isEmpty(title)) {
            out.println("");
            out.println("    \\maketitle");
            out.println("    \\clearpage");
        }

        if (tableOfContents) {
            out.println("");
            out.println("    \\tableofcontents");
            out.println("    \\clearpage");
        }

        out.println();
        out.printf("    \\fontsize{%s}{%s}\n", fontSize, baseLineStrech);
        out.println();

        havePars(doc);

        if (appendix) {
            out.println();
            out.println("    \\clearpage");
            out.println("    \\appendix");
        }

        out.println();
        out.println("\\end{document}");
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        for (Part part : partGroup.parts)
            part.accept(this);
    }

    @Override
    public void part(Part part, int index) {
        // reserved # for title.
        PartLevel level = part.getLevel().toLatex();
        String title = part.title.getText();
        String mlTitle = mlEscape(title);

        out.printf("\\%s {%s}\n", level, mlTitle);
        out.enter();
        havePars(part);
        out.leave();
        out.println();
    }

    @Override
    public void list(ListPar list) {
        if (list.isOrdered())
            out.enterln("\\begin{enumerate}");
        else
            out.enterln("\\begin{itemize}");

        havePars(list);

        if (list.isOrdered())
            out.leaveln("\\end{enumerate}");
        else
            out.leaveln("\\end{itemize}");
    }

    @Override
    public void listItem(ListItem item, int index, int itemIndex) {
        out.print("\\item ");
        havePars(item);
    }

    @Override
    public void table(Table table) {
        StringBuilder mods = new StringBuilder();// "@{}lllrl@{}";
        if (table.firstRows > 0) {
            TableRow firstRow = table.rows.get(0);
            int nCols = firstRow.cells.size();

            for (int i = 0; i < nCols; i++) {
                TableCell cell = firstRow.cells.get(i);
                // boolean lastColumn = i == nCols - 1;
                // TableCell nextCell = lastColumn ? null : firstRow.cells.get(i + 1);

                if (cell.borderLeft)
                    mods.append('|');

                String align = latexTableFieldAlign(cell.getAlignment());
                mods.append(align);

                if (cell.borderRight)
                    mods.append("|");
            }
        }

        out.printf("\\begin{longtable}[]{%s}\n", mods);
        out.enter();

        for (TableRow row : table.rows)
            row.accept(this);

        out.println("\\bottomrule");
        out.leave();
        out.println("\\end{longtable}");
    }

    @Override
    public void tableRow(TableRow row, int rowIndex) {
        Table table = row.getTable();
        String rule = "\\tabularnewline";
        switch (table.getRowPosition(rowIndex)) {
        case TOP:
            rule = "\\toprule";
            break;
        case BOTTOM:
            rule = "\\bottomrule";
            break;
        default:
        }

        if (row.borderTop)
            out.println(rule);

        int nCell = row.cells.size();
        for (int i = 0; i < nCell; i++) {
            if (i > 0)
                out.print(" & ");
            TableCell cell = row.cells.get(i);
            cell.accept(this);
        }

        if (row.borderBottom)
            out.println(rule);

        if (rowIndex == table.firstRows - 1) {
            out.println("\\midrule");
            out.println("\\endhead");
        }

        out.println();
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        CellTextParHandler handler = new CellTextParHandler();
        textParHandlerStack.push(handler);
        super.havePars(cell);
        textParHandlerStack.pop();
    }

    class CellTextParHandler
            implements
                TextParHandler {
        @Override
        public void handleTextPar(TextPar textPar) {
            haveRuns(textPar);
            out.println();
        }
    }

    @Override
    public void textBox(TextBox textBox) {
        havePars(textBox);
    }

    @Override
    public void textPar(TextPar textPar) {
        if (!textParHandlerStack.isEmpty()) {
            textParHandlerStack.top().handleTextPar(textPar);
            return;
        }

        haveRuns(textPar);
        out.println();
        out.println();
    }

    @Override
    public void textRun(TextRun textRun) {
        for (String s : textRun.textList) {
            out.print(s);
        }
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        haveRuns(runGroup);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        haveRuns(fontEnv);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        if (fontStyleEnv.isBold())
            out.print("\\textbf{");
        if (fontStyleEnv.isItalic())
            out.print("\\textit{");
        if (fontStyleEnv.isUnderline())
            out.print("\\underline{");
        if (fontStyleEnv.isStrikeline())
            out.print("\\sout{");

        haveRuns(fontStyleEnv);

        if (fontStyleEnv.isBold())
            out.print("}");
        if (fontStyleEnv.isItalic())
            out.print("}");
        if (fontStyleEnv.isUnderline())
            out.print("}");
        if (fontStyleEnv.isStrikeline())
            out.print("}");
    }

    @Override
    public void hr(Hr hr) {
        out.println();
        out.println("\\noindent\\makebox[\\linewidth]{\\rule{\\paperwidth}{0.4pt}}");
        out.println();
    }

    @Override
    public void image(Image image) {
        String name = image.getName();
        String href = image.getHref();
        String description = image.getDescription();

        out.enterln("\\begin{figure}[H] {");
        out.println("\\centering");
        out.printf("\\includegraphics{%s}\n", href);
        if (description != null)
            out.printf("\\caption{%s}\n", description);
        if (name != null)
            out.printf("\\label{fig:%s}\n", name);
        out.println("} \\end{figure}");
    }

    String latexTableFieldAlign(HorizAlignment alignment) {
        switch (alignment) {
        case LEFT:
            return "l";
        case RIGHT:
            return "r";
        case CENTER:
            return "c";
        case FILL:
        default:
            return "l";
        }
    }

}