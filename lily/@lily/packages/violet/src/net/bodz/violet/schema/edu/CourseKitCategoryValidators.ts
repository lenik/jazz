import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type CourseKitCategoryTypeInfo from "./CourseKitCategoryTypeInfo";
import _CourseKitCategory_stuff_Validators from "./_CourseKitCategory_stuff_Validators";

export class CourseKitCategoryValidators extends _CourseKitCategory_stuff_Validators {

    constructor(type: CourseKitCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseKitCategoryTypeInfo;
    }

}

export default CourseKitCategoryValidators;
