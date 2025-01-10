import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
