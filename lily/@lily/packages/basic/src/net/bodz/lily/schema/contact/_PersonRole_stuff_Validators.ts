import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import type Person from "./Person";
import type _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class _PersonRole_stuff_Validators extends IdEntityValidators {

    constructor(type: _PersonRole_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonRole_stuff_TypeInfo;
    }

    validateRole(val: string) {
    }

    validateOrgUnit(val: OrgUnit) {
    }

    validatePerson(val: Person) {
    }

    validateOrg(val: Organization) {
    }

}

export default _PersonRole_stuff_Validators;
