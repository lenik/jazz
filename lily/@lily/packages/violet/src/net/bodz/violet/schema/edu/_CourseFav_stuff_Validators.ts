import { ValidateResult } from "@skeljs/core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type Course from "./Course";
import type _CourseFav_stuff_TypeInfo from "./_CourseFav_stuff_TypeInfo";

export class _CourseFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _CourseFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CourseFav_stuff_TypeInfo;
    }

    validateCourse(val: Course) {
    }

}

export default _CourseFav_stuff_Validators;
