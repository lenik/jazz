import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type Course from "./Course";
import type CourseKitCategory from "./CourseKitCategory";
import type _CourseKit_stuff_TypeInfo from "./_CourseKit_stuff_TypeInfo";

export class _CourseKit_stuff_Validators extends CoImagedValidators {

    constructor(type: _CourseKit_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CourseKit_stuff_TypeInfo;
    }

    validateFavCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateHateCount(val: int) {
    }

    validateDummy(val: JsonVariant) {
    }

    validateCategory(val: CourseKitCategory) {
    }

    validateCourse(val: Course) {
    }

}

export default _CourseKit_stuff_Validators;
