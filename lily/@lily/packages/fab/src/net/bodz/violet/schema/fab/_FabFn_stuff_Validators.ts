import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoImagedValidators from "lily-basic/src/net/bodz/lily/concrete/CoImagedValidators";

import type FabFn from "./FabFn";
import type _FabFn_stuff_TypeInfo from "./_FabFn_stuff_TypeInfo";

export class _FabFn_stuff_Validators extends CoImagedValidators {

    constructor(type: _FabFn_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabFn_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validateDepth(val: int) {
    }

    validateRefCount(val: int) {
    }

    validateParent(val: FabFn) {
    }

}

export default _FabFn_stuff_Validators;
