import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PersonTypeInfo from "./PersonTypeInfo";
import _Person_stuff_Validators from "./_Person_stuff_Validators";

export class PersonValidators extends _Person_stuff_Validators {

    constructor(type: PersonTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PersonTypeInfo;
    }

    validateHello(val: string) {
    }

    validatePeers(val: string[]) {
    }

}

export default PersonValidators;
