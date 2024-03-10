import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import CoEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/CoEntityValidators";

import type FabProcess from "./FabProcess";
import type _FabProcessSerial_stuff_TypeInfo from "./_FabProcessSerial_stuff_TypeInfo";

export class _FabProcessSerial_stuff_Validators extends CoEntityValidators {

    constructor(type: _FabProcessSerial_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabProcessSerial_stuff_TypeInfo;
    }

    validateId(val: long) {
    }

    validateSerial(val: string) {
    }

    validateProcess(val: FabProcess) {
    }

}

export default _FabProcessSerial_stuff_Validators;
