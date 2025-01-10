import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
