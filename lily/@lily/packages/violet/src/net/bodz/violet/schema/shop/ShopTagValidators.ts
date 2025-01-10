import { ValidateResult } from "skel01-core/src/ui/types";

import type ShopTagTypeInfo from "./ShopTagTypeInfo";
import _ShopTag_stuff_Validators from "./_ShopTag_stuff_Validators";

export class ShopTagValidators extends _ShopTag_stuff_Validators {

    constructor(type: ShopTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ShopTagTypeInfo;
    }

}

export default ShopTagValidators;
