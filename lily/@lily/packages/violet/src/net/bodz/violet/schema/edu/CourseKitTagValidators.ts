import { ValidateResult } from "skel01-core/src/ui/types";

import type CourseKitTagTypeInfo from "./CourseKitTagTypeInfo";
import _CourseKitTag_stuff_Validators from "./_CourseKitTag_stuff_Validators";

export class CourseKitTagValidators extends _CourseKitTag_stuff_Validators {

    constructor(type: CourseKitTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseKitTagTypeInfo;
    }

}

export default CourseKitTagValidators;
