import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";

import type FabProcess from "./FabProcess";
import type _FabProcessSerial_stuff_TypeInfo from "./_FabProcessSerial_stuff_TypeInfo";

export class _FabProcessSerial_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabProcessSerial_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabProcessSerial_stuff_TypeInfo;
    }

    validateSerial(val: string) {
    }

    validateProcess(val: FabProcess) {
    }

}

export default _FabProcessSerial_stuff_Validators;
