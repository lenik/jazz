import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import type Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _GroupAsset_stuff_TypeInfo from "./_GroupAsset_stuff_TypeInfo";

export class _GroupAsset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _GroupAsset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _GroupAsset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateOwner(val: Group) {
    }

}

export default _GroupAsset_stuff_Validators;
