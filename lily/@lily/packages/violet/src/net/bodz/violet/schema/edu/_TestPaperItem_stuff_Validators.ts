import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoImagedValidators from "@lily/basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type TestPaper from "./TestPaper";
import type TestQuestion from "./TestQuestion";
import type _TestPaperItem_stuff_TypeInfo from "./_TestPaperItem_stuff_TypeInfo";

export class _TestPaperItem_stuff_Validators extends CoImagedValidators {

    constructor(type: _TestPaperItem_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestPaperItem_stuff_TypeInfo;
    }

    validateScore(val: BigDecimal) {
    }

    validateQuestion(val: TestQuestion) {
    }

    validatePaper(val: TestPaper) {
    }

}

export default _TestPaperItem_stuff_Validators;
