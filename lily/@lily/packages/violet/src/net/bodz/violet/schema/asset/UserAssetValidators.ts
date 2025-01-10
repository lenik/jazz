import { ValidateResult } from "skel01-core/src/ui/types";

import type UserAssetTypeInfo from "./UserAssetTypeInfo";
import _UserAsset_stuff_Validators from "./_UserAsset_stuff_Validators";

export class UserAssetValidators extends _UserAsset_stuff_Validators {

    constructor(type: UserAssetTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserAssetTypeInfo;
    }

}

export default UserAssetValidators;
