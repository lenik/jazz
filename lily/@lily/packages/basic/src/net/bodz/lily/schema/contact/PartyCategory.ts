import _PartyCategory_stuff from "./_PartyCategory_stuff";
import { _PartyCategory_stuff_Type } from "./_PartyCategory_stuff_Type";

export class PartyCategory extends _PartyCategory_stuff<PartyCategory> {
    static TYPE = new _PartyCategory_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PartyCategory;
