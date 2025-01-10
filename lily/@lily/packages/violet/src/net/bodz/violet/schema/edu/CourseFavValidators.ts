import { ValidateResult } from "skel01-core/src/ui/types";

import type CourseFavTypeInfo from "./CourseFavTypeInfo";
import _CourseFav_stuff_Validators from "./_CourseFav_stuff_Validators";

export class CourseFavValidators extends _CourseFav_stuff_Validators {

    constructor(type: CourseFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as CourseFavTypeInfo;
    }

}

export default CourseFavValidators;
