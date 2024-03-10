import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _UserAsset_stuff_TypeInfo from "./_UserAsset_stuff_TypeInfo";

export class _UserAsset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _UserAsset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _UserAsset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateOwner(val: User) {
    }

}

export default _UserAsset_stuff_Validators;
