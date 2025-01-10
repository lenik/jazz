import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoCategoryValidators from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _FabStdTestCategory_stuff_TypeInfo from "./_FabStdTestCategory_stuff_TypeInfo";

export class _FabStdTestCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _FabStdTestCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdTestCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _FabStdTestCategory_stuff_Validators;
