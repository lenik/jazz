import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type User from "../account/User";
import type Badge from "./Badge";
import type _UserBadge_stuff_TypeInfo from "./_UserBadge_stuff_TypeInfo";

export class _UserBadge_stuff_Validators extends IdEntityValidators {

    constructor(type: _UserBadge_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserBadge_stuff_TypeInfo;
    }

    validateBadge(val: Badge) {
    }

    validateUser(val: User) {
    }

}

export default _UserBadge_stuff_Validators;
