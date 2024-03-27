import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTagValidators from "@lily/basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _CourseTag_stuff_TypeInfo from "./_CourseTag_stuff_TypeInfo";

export class _CourseTag_stuff_Validators extends CoTagValidators {

    constructor(type: _CourseTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _CourseTag_stuff_TypeInfo;
    }

}

export default _CourseTag_stuff_Validators;