import type { int, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type Course from "./Course";
import type _TestQuestion_stuff_TypeInfo from "./_TestQuestion_stuff_TypeInfo";

export class _TestQuestion_stuff_Validators extends CoMessageValidators {

    constructor(type: _TestQuestion_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestion_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateFavCount(val: int) {
    }

    validateVoteCount(val: int) {
    }

    validateHateCount(val: int) {
    }

    validatePos(val: int) {
    }

    validateAnswer(val: string) {
    }

    validateCourse(val: Course) {
    }

}

export default _TestQuestion_stuff_Validators;
