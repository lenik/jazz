import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type Person from "./Person";
import type PersonTagType from "./PersonTagType";
import type _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class _PersonTag_stuff_Validators extends CoEntityValidators {

    constructor(type: _PersonTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonTag_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateTag(val: PersonTagType) {
    }

    validatePerson(val: Person) {
    }

}

export default _PersonTag_stuff_Validators;
