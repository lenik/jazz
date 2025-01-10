import { ValidateResult } from "skel01-core/src/ui/types";
import FavRecordValidators from "@lily/basic/src/net/bodz/lily/concrete/FavRecordValidators";

import type TestQuestion from "./TestQuestion";
import type _TestQuestionFav_stuff_TypeInfo from "./_TestQuestionFav_stuff_TypeInfo";

export class _TestQuestionFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _TestQuestionFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TestQuestionFav_stuff_TypeInfo;
    }

    validateTestq(val: TestQuestion) {
    }

}

export default _TestQuestionFav_stuff_Validators;
