import { ValidateResult } from "@skeljs/core/src/ui/types";

import type AssetTypeInfo from "./AssetTypeInfo";
import _Asset_stuff_Validators from "./_Asset_stuff_Validators";

export class AssetValidators extends _Asset_stuff_Validators {

    constructor(type: AssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as AssetTypeInfo;
    }

}

export default AssetValidators;
