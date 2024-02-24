import PartyCategoryTypeInfo from "./PartyCategoryTypeInfo";
import _PartyCategory_stuff from "./_PartyCategory_stuff";

export class PartyCategory extends _PartyCategory_stuff<PartyCategory> {
    static TYPE = new PartyCategoryTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PartyCategory;
