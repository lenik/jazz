import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "lily-basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type Course from "./Course";
import type _TestPaper_stuff_TypeInfo from "./_TestPaper_stuff_TypeInfo";

export class _TestPaper_stuff_Validators extends CoMessageValidators {

    constructor(type: _TestPaper_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestPaper_stuff_TypeInfo;
    }

    validateTimeout(val: int) {
    }

    validateTotalscore(val: int) {
    }

    validateCourse(val: Course) {
    }

}

export default _TestPaper_stuff_Validators;
