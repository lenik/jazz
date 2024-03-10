import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type FabProcessSerialTypeInfo from "./FabProcessSerialTypeInfo";
import _FabProcessSerial_stuff_Validators from "./_FabProcessSerial_stuff_Validators";

export class FabProcessSerialValidators extends _FabProcessSerial_stuff_Validators {

    constructor(type: FabProcessSerialTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as FabProcessSerialTypeInfo;
    }

}

export default FabProcessSerialValidators;
