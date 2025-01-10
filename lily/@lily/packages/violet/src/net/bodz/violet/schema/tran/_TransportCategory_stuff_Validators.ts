import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoCategoryValidators from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryValidators";

import type _TransportCategory_stuff_TypeInfo from "./_TransportCategory_stuff_TypeInfo";

export class _TransportCategory_stuff_Validators extends CoCategoryValidators {

    constructor(type: _TransportCategory_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _TransportCategory_stuff_TypeInfo;
    }

    validateName(val: string) {
    }

}

export default _TransportCategory_stuff_Validators;
