import { Contact } from "./Contact";
import { PersonRole } from "./PersonRole";
import _OrgUnit_stuff from "./_OrgUnit_stuff";
import { _OrgUnit_stuff_Type } from "./_OrgUnit_stuff_Type";

export class OrgUnit extends _OrgUnit_stuff {
    static TYPE = new _OrgUnit_stuff_Type();

    contact?: Contact
    staff?: PersonRole[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default OrgUnit;
