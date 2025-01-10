import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type User from "./User";
import type _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class _UserSecret_stuff_Validators extends IdEntityValidators {

    constructor(type: _UserSecret_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserSecret_stuff_TypeInfo;
    }

    validateProperties(val: JsonVariant) {
    }

    validatePassword(val: string) {
    }

    validateQuestion(val: string) {
    }

    validateAnswer(val: string) {
    }

    validateUser(val: User) {
    }

}

export default _UserSecret_stuff_Validators;
