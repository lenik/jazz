import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type CourseTypeInfo from "./CourseTypeInfo";
import _Course_stuff_Validators from "./_Course_stuff_Validators";

export class CourseValidators extends _Course_stuff_Validators {

    constructor(type: CourseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseTypeInfo;
    }

}

export default CourseValidators;
