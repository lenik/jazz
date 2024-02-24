import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type User from "../account/User";
import type Badge from "./Badge";
import type _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class _UserBadge_stuff_Validators extends CoEntityValidators {

    constructor(type: _UserBadge_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserBadge_stuff_TypeInfo;
    }

    validateId(val: integer) {
    }

    validateBadge(val: Badge) {
    }

    validateUser(val: User) {
    }

}

export default _UserBadge_stuff_Validators;
