import { ValidateResult } from "@skeljs/core/src/ui/types";

import type CourseTagTypeInfo from "./CourseTagTypeInfo";
import _CourseTag_stuff_Validators from "./_CourseTag_stuff_Validators";

export class CourseTagValidators extends _CourseTag_stuff_Validators {

    constructor(type: CourseTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseTagTypeInfo;
    }

}

export default CourseTagValidators;
