
import type { List } from "../../../../../java/util/List";
import type { Contact } from "./Contact";
import { * as validators } from "./PersonValidators";
import type { _OrgUnit_stuff } from "./_OrgUnit_stuff";

export class OrgUnit extends _OrgUnit_stuff {
    static TYPE = new OrgUnitType();

    contact?: Contact
    staff?: List

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
