import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ShopTypeInfo from "./ShopTypeInfo";
import _Shop_stuff_Validators from "./_Shop_stuff_Validators";

export class ShopValidators extends _Shop_stuff_Validators {

    constructor(type: ShopTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopTypeInfo;
    }

}

export default ShopValidators;
