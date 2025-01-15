import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "lily-basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type TestQuestion from "./TestQuestion";
import type _TestAnswer_stuff_TypeInfo from "./_TestAnswer_stuff_TypeInfo";

export class _TestAnswer_stuff_Validators extends CoMessageValidators {

    constructor(type: _TestAnswer_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestAnswer_stuff_TypeInfo;
    }

    validateValid(val: boolean) {
    }

    validateQuestion(val: TestQuestion) {
    }

}

export default _TestAnswer_stuff_Validators;
