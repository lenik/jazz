import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type StorageTypeInfo from "./StorageTypeInfo";
import _Storage_stuff_Validators from "./_Storage_stuff_Validators";

export class StorageValidators extends _Storage_stuff_Validators {

    constructor(type: StorageTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StorageTypeInfo;
    }

}

export default StorageValidators;
