package user;

public class School {

    /**
     * School Identifier.
     * 
     * @style color: blue; background-color: #ccccff
     */
    public final String id;

    public School(String id) {
        this.id = id;
    }

    /**
     * @name Lucy Girl
     * @style border: 1px solid
     */
    public SimplePerson lucy;

    /**
     * @name Lily Girl
     */
    public ComplexPerson lily;

    @Override
    public String toString() {
        return "Lucy is " + lucy + "\nLily is " + lily;
    }

}
