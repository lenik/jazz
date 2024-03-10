import { ValidateResult } from "@skeljs/core/src/ui/types";

import type OrgUnitAssetTypeInfo from "./OrgUnitAssetTypeInfo";
import _OrgUnitAsset_stuff_Validators from "./_OrgUnitAsset_stuff_Validators";

export class OrgUnitAssetValidators extends _OrgUnitAsset_stuff_Validators {

    constructor(type: OrgUnitAssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as OrgUnitAssetTypeInfo;
    }

}

export default OrgUnitAssetValidators;
