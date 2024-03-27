import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ArtifactCategoryTypeInfo from "./ArtifactCategoryTypeInfo";
import _ArtifactCategory_stuff_Validators from "./_ArtifactCategory_stuff_Validators";

export class ArtifactCategoryValidators extends _ArtifactCategory_stuff_Validators {

    constructor(type: ArtifactCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactCategoryTypeInfo;
    }

}

export default ArtifactCategoryValidators;