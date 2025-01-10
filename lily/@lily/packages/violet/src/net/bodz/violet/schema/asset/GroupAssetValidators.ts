import { ValidateResult } from "skel01-core/src/ui/types";

import type GroupAssetTypeInfo from "./GroupAssetTypeInfo";
import _GroupAsset_stuff_Validators from "./_GroupAsset_stuff_Validators";

export class GroupAssetValidators extends _GroupAsset_stuff_Validators {

    constructor(type: GroupAssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as GroupAssetTypeInfo;
    }

}

export default GroupAssetValidators;
