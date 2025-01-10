import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type Person from "./Person";
import type PersonTagType from "./PersonTagType";
import type _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class _PersonTag_stuff_Validators extends IdEntityValidators {

    constructor(type: _PersonTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonTag_stuff_TypeInfo;
    }

    validateTag(val: PersonTagType) {
    }

    validatePerson(val: Person) {
    }

}

export default _PersonTag_stuff_Validators;
