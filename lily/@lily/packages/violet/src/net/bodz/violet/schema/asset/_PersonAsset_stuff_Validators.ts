import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import type Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _PersonAsset_stuff_TypeInfo from "./_PersonAsset_stuff_TypeInfo";

export class _PersonAsset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _PersonAsset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PersonAsset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateOwner(val: Person) {
    }

}

export default _PersonAsset_stuff_Validators;
