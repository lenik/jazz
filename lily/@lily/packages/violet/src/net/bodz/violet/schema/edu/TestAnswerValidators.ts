import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type TestAnswerTypeInfo from "./TestAnswerTypeInfo";
import _TestAnswer_stuff_Validators from "./_TestAnswer_stuff_Validators";

export class TestAnswerValidators extends _TestAnswer_stuff_Validators {

    constructor(type: TestAnswerTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestAnswerTypeInfo;
    }

}

export default TestAnswerValidators;
