import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type BadgeTypeInfo from "./BadgeTypeInfo";
import _Badge_stuff_Validators from "./_Badge_stuff_Validators";

export class BadgeValidators extends _Badge_stuff_Validators {

    constructor(type: BadgeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as BadgeTypeInfo;
    }

}

export default BadgeValidators;
