
import { * as validators } from "./PersonValidators";
import type { _PartyCategory_stuff } from "./_PartyCategory_stuff";

export class PartyCategory extends _PartyCategory_stuff<PartyCategory> {
    static TYPE = new PartyCategoryType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
