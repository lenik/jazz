import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import { ValidateResult } from "@skeljs/core/src/ui/types";
import type OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _OrgUnitAsset_stuff_TypeInfo from "./_OrgUnitAsset_stuff_TypeInfo";

export class _OrgUnitAsset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _OrgUnitAsset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _OrgUnitAsset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateOwner(val: OrgUnit) {
    }

}

export default _OrgUnitAsset_stuff_Validators;
