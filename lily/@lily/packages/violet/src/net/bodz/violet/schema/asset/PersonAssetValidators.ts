import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PersonAssetTypeInfo from "./PersonAssetTypeInfo";
import _PersonAsset_stuff_Validators from "./_PersonAsset_stuff_Validators";

export class PersonAssetValidators extends _PersonAsset_stuff_Validators {

    constructor(type: PersonAssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PersonAssetTypeInfo;
    }

}

export default PersonAssetValidators;
