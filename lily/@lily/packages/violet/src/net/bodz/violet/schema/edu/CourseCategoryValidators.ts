import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type CourseCategoryTypeInfo from "./CourseCategoryTypeInfo";
import _CourseCategory_stuff_Validators from "./_CourseCategory_stuff_Validators";

export class CourseCategoryValidators extends _CourseCategory_stuff_Validators {

    constructor(type: CourseCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseCategoryTypeInfo;
    }

}

export default CourseCategoryValidators;
