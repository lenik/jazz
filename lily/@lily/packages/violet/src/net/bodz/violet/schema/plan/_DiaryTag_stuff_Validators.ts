import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoTagValidators from "@lily/basic/src/net/bodz/lily/concrete/CoTagValidators";

import type _DiaryTag_stuff_TypeInfo from "./_DiaryTag_stuff_TypeInfo";

export class _DiaryTag_stuff_Validators extends CoTagValidators {

    constructor(type: _DiaryTag_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryTag_stuff_TypeInfo;
    }

}

export default _DiaryTag_stuff_Validators;
