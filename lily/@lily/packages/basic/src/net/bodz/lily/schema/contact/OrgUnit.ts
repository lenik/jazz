import Contact from "./Contact";
import OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import PersonRole from "./PersonRole";
import _OrgUnit_stuff from "./_OrgUnit_stuff";

export class OrgUnit extends _OrgUnit_stuff {
    static TYPE = new OrgUnitTypeInfo();

    contact?: Contact
    staff?: PersonRole[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default OrgUnit;
