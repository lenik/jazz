import type { List } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type Contact from "./Contact";
import type OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import type PersonRole from "./PersonRole";
import _OrgUnit_stuff_Validators from "./_OrgUnit_stuff_Validators";

export class OrgUnitValidators extends _OrgUnit_stuff_Validators {

    constructor(type: OrgUnitTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as OrgUnitTypeInfo;
    }

    validateContact(val: Contact) {
    }

    validateStaff(val: List<PersonRole>) {
    }

}

export default OrgUnitValidators;
