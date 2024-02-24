import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type User from "./User";
import type _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class _UserSecret_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserSecret_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserSecret_stuff_TypeInfo;
    }

    validateId(val: integer) {
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
