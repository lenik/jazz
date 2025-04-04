import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type SellPriceTypeInfo from "./SellPriceTypeInfo";
import _SellPrice_stuff_Validators from "./_SellPrice_stuff_Validators";

export class SellPriceValidators extends _SellPrice_stuff_Validators {

    constructor(type: SellPriceTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SellPriceTypeInfo;
    }

}

export default SellPriceValidators;
