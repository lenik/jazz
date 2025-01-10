import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type CourseKitTypeInfo from "./CourseKitTypeInfo";
import _CourseKit_stuff_Validators from "./_CourseKit_stuff_Validators";

export class CourseKitValidators extends _CourseKit_stuff_Validators {

    constructor(type: CourseKitTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseKitTypeInfo;
    }

}

export default CourseKitValidators;
