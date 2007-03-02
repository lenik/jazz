package net.bodz.xml.xmod.modpdb;

import net.sf.freejava.fp.dump.XMLDump;

public class ForeignKey extends Index {

    /** free reference, for describing purpose */
    public static final int FREE         = 0;

    /** force keeping integrity, forbidden referent's delete/update */
    public static final int INTEGRITY    = 1;

    /** delete/update selves when referent deleted/updated */
    public static final int CASCADE      = 1;

    /** set selves to null when referent deleted/updated */
    public static final int SET_NULL     = 2;

    /** 1:1 relation */
    public static final int ONE_TO_ONE   = 0;

    /** 1:n relation */
    public static final int ONE_TO_MANY  = 1;

    /** n:1 relation */
    public static final int MANY_TO_ONE  = 2;

    /** n:m relation */
    public static final int MANY_TO_MANY = 3;

    protected FieldsPartial ref;

    protected int           relation;

    @XMLDump
    protected int           deleteBehav;

    @XMLDump
    protected int           updateBehav;

    @XMLDump
    protected String        disp;

    public ForeignKey(Table table, int[] indexes, Table refTable,
            int[] refIndexes) {
        super(table, indexes);
        setRef(refTable, refIndexes);
    }

    public ForeignKey(Table table, String[] names, Table refTable,
            String[] refNames) {
        super(table, names);
        setRef(refTable, refNames);
    }

    public ForeignKey(ForeignKey base, int index1, int refIndex1) {
        super(base, index1);
        setDisp(base.disp);
        setRef(new FieldsPartial(base.ref, refIndex1));
    }

    public ForeignKey(ForeignKey base, String name1, String refName1) {
        super(base, name1);
        setDisp(base.disp);
        setRef(new FieldsPartial(base.ref, refName1));
    }

    public Fields getRef() {
        return ref;
    }

    public void setRef(FieldsPartial ref) {
        this.ref = ref;
    }

    public void setRef(Table table, int... indexes) {
        setRef(new FieldsPartial(table, indexes));
    }

    public void setRef(Table table, String... names) {
        setRef(new FieldsPartial(table, names));
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        if (relation < 0 || relation >= _RelationNames.length)
            throw new IllegalArgumentException("Invalid relation number: "
                    + relation);
        this.relation = relation;
    }

    public int getDeleteBehav() {
        return deleteBehav;
    }

    public void setDeleteBehav(int behav) {
        if (behav < 0 || behav >= _BehavNames.length)
            throw new IllegalArgumentException("Invalid behav number: " + behav);
        this.deleteBehav = behav;
    }

    public int getUpdateBehav() {
        return updateBehav;
    }

    public void setUpdateBehav(int behav) {
        if (behav < 0 || behav >= _BehavNames.length)
            throw new IllegalArgumentException("Invalid behav number: " + behav);
        this.updateBehav = behav;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    private static final String[] _RelationNames;
    private static final String[] _BehavNames;
    static {
        _RelationNames = new String[] { "1:1", "1:n", "n:1", "n:m", };
        _BehavNames = new String[] { "->", "-->", "=>", ">>", }; // TODO
    }

    public static String getRelationName(int relation) {
        return _RelationNames[relation];
    }

    public static String getBehavName(int behav) {
        return _BehavNames[behav];
    }

    public static int parseRelation(String relation) { // TODO
        for (int i = 0; i < _RelationNames.length; i++)
            if (_RelationNames[i].equals(relation))
                return i;
        return -1;
    }

    public static int parseBehav(String behav) {
        for (int i = 0; i < _BehavNames.length; i++)
            if (_BehavNames[i].equals(behav))
                return i;
        return -1;
    }

    @Override
    public String toString() {
        // S<x,y,z> (del)-> n:1 (upd)-> F<u,v,w>
        String self = super.toString();
        String foreign = ref.toString();
        String rel = getRelationName(relation);
        String del = getBehavName(deleteBehav);
        String upd = getBehavName(deleteBehav);
        return self + " " + del + " " + rel + " " + upd + " " + foreign;
    }

}
