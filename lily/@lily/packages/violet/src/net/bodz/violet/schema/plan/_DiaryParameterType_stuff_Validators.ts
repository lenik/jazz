import { ValidateResult } from "skel01-core/src/ui/types";
import CoParameterValidators from "@lily/basic/src/net/bodz/lily/concrete/CoParameterValidators";

import type _DiaryParameterType_stuff_TypeInfo from "./_DiaryParameterType_stuff_TypeInfo";

export class _DiaryParameterType_stuff_Validators extends CoParameterValidators {

    constructor(type: _DiaryParameterType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryParameterType_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _DiaryParameterType_stuff_Validators;
