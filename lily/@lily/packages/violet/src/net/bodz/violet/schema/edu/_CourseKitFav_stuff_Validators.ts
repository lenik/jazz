import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type CourseKit from "./CourseKit";
import type _CourseKitFav_stuff_TypeInfo from "./_CourseKitFav_stuff_TypeInfo";

export class _CourseKitFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _CourseKitFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CourseKitFav_stuff_TypeInfo;
    }

    validateCoursekit(val: CourseKit) {
    }

}

export default _CourseKitFav_stuff_Validators;
