import _PartyCategory_stuff from "./_PartyCategory_stuff";
import { _PartyCategory_stuffTypeInfo } from "./_PartyCategory_stuffTypeInfo";

export class PartyCategory extends _PartyCategory_stuff<PartyCategory> {
    static TYPE = new _PartyCategory_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PartyCategory;
