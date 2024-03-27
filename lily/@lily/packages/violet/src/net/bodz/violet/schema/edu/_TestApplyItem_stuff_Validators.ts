import type { double, int, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type TestApply from "./TestApply";
import type TestQuestion from "./TestQuestion";
import type _TestApplyItem_stuff_TypeInfo from "./_TestApplyItem_stuff_TypeInfo";

export class _TestApplyItem_stuff_Validators extends CoEntityValidators {

    constructor(type: _TestApplyItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestApplyItem_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateAnswer(val: int) {
    }

    validateAnstext(val: string) {
    }

    validateScore(val: double) {
    }

    validateWaittime(val: double) {
    }

    validateQuestion(val: TestQuestion) {
    }

    validateApply(val: TestApply) {
    }

}

export default _TestApplyItem_stuff_Validators;