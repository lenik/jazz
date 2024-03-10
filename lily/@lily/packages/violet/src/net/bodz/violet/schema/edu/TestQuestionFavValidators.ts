import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TestQuestionFavTypeInfo from "./TestQuestionFavTypeInfo";
import _TestQuestionFav_stuff_Validators from "./_TestQuestionFav_stuff_Validators";

export class TestQuestionFavValidators extends _TestQuestionFav_stuff_Validators {

    constructor(type: TestQuestionFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TestQuestionFavTypeInfo;
    }

}

export default TestQuestionFavValidators;
