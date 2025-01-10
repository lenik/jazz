import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import { ValidateResult } from "skel01-core/src/ui/types";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";

import AbstractAssetValidators from "./AbstractAssetValidators";
import type _OrgAsset_stuff_TypeInfo from "./_OrgAsset_stuff_TypeInfo";

export class _OrgAsset_stuff_Validators extends AbstractAssetValidators {

    constructor(type: _OrgAsset_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _OrgAsset_stuff_TypeInfo;
    }

    validateBatch(val: JsonVariant) {
    }

    validateOwner(val: Organization) {
    }

}

export default _OrgAsset_stuff_Validators;
