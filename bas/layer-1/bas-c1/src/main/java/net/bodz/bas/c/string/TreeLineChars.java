package net.bodz.bas.c.string;

public class TreeLineChars {

    public String treeSkip = "    ";
    public String treeLine;
    public String treeBranch;
    public String treeLastBranch;

    public TreeLineChars(String treeLine, String treeBranch, String treeLastBranch) {
        this.treeLine = treeLine;
        this.treeBranch = treeBranch;
        this.treeLastBranch = treeLastBranch;
    }

    public static final TreeLineChars ascii = new TreeLineChars(" |  ", " |- ", " `- ");
    public static final TreeLineChars rigid = new TreeLineChars(" │  ", " ├╴ ", " └╴ ");
    public static final TreeLineChars smooth = new TreeLineChars(" │  ", " ├╴ ", " ╰╴ ");
    public static final TreeLineChars bold = new TreeLineChars(" ┃  ", " ┣╸ ", " ┗╸ ");
    public static final TreeLineChars doubl = new TreeLineChars(" ║  ", " ╠═ ", " ╚═ ");
    public static final TreeLineChars doublH = new TreeLineChars(" │  ", " ╞═ ", " ╘═ ");
    public static final TreeLineChars doublV = new TreeLineChars(" ║  ", " ╟╴", " ╙╴ ");
    public static final TreeLineChars dashed = new TreeLineChars(" ┊  ", " ┊╌ ", " ╵╌ ");
    public static final TreeLineChars blind = new TreeLineChars(" ⡇  ", " ⡧⠄ ", " ⠣⠄ ");
    public static final TreeLineChars mosaic = new TreeLineChars(" ▌  ", " ▙▄ ", " ▚▄ ");

}
