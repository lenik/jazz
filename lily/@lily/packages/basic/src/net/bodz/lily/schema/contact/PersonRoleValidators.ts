import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PersonRoleTypeInfo from "./PersonRoleTypeInfo";
import _PersonRole_stuff_Validators from "./_PersonRole_stuff_Validators";

export class PersonRoleValidators extends _PersonRole_stuff_Validators {

    constructor(type: PersonRoleTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PersonRoleTypeInfo;
    }

}

export default PersonRoleValidators;
