import { ValidateResult } from "skel01-core/src/ui/types";

import type CourseKitFavTypeInfo from "./CourseKitFavTypeInfo";
import _CourseKitFav_stuff_Validators from "./_CourseKitFav_stuff_Validators";

export class CourseKitFavValidators extends _CourseKitFav_stuff_Validators {

    constructor(type: CourseKitFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseKitFavTypeInfo;
    }

}

export default CourseKitFavValidators;
