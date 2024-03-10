import type { BigDecimal, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import type TestPaper from "./TestPaper";
import type _TestApply_stuff_TypeInfo from "./_TestApply_stuff_TypeInfo";

export class _TestApply_stuff_Validators extends CoMessageValidators {

    constructor(type: _TestApply_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestApply_stuff_TypeInfo;
    }

    validateScore(val: BigDecimal) {
    }

    validatePerson(val: Person) {
    }

    validatePaper(val: TestPaper) {
    }

}

export default _TestApply_stuff_Validators;
