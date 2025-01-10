import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type CourseCategory from "./CourseCategory";
import type _Course_stuff_TypeInfo from "./_Course_stuff_TypeInfo";

export class _Course_stuff_Validators extends CoImagedValidators {

    constructor(type: _Course_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Course_stuff_TypeInfo;
    }

    validateFavCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateHateCount(val: int) {
    }

    validateCredit(val: int) {
    }

    validatePlugins(val: JsonVariant) {
    }

    validateCategory(val: CourseCategory) {
    }

}

export default _Course_stuff_Validators;
