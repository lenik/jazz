import { ValidateResult } from "@skeljs/core/src/ui/types";

import type OrgAssetTypeInfo from "./OrgAssetTypeInfo";
import _OrgAsset_stuff_Validators from "./_OrgAsset_stuff_Validators";

export class OrgAssetValidators extends _OrgAsset_stuff_Validators {

    constructor(type: OrgAssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as OrgAssetTypeInfo;
    }

}

export default OrgAssetValidators;
