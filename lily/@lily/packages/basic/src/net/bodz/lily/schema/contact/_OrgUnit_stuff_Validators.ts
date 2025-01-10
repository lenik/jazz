import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import PartyValidators from "./PartyValidators";
import type _OrgUnit_stuff_TypeInfo from "./_OrgUnit_stuff_TypeInfo";

export class _OrgUnit_stuff_Validators extends PartyValidators {

    constructor(type: _OrgUnit_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _OrgUnit_stuff_TypeInfo;
    }

    validateDepth(val: int) {
    }

    validateOrg(val: Organization) {
    }

    validateParent(val: OrgUnit) {
    }

}

export default _OrgUnit_stuff_Validators;
