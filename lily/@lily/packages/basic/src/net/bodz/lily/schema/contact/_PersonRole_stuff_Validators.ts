import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type OrgUnit from "./OrgUnit";
import type Organization from "./Organization";
import type Person from "./Person";
import type _PersonRole_stuff_TypeInfo from "./_PersonRole_stuff_TypeInfo";

export class _PersonRole_stuff_Validators extends CoEntityValidators {

    constructor(type: _PersonRole_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonRole_stuff_TypeInfo;
    }

    validateId(val: int) {
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
