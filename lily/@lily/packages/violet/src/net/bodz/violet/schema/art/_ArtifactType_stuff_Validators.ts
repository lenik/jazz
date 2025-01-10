import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type UomRow from "@lily/basic/src/net/bodz/lily/schema/util/UomRow";

import type ArtifactCategory from "./ArtifactCategory";
import type ArtifactType from "./ArtifactType";
import type _ArtifactType_stuff_TypeInfo from "./_ArtifactType_stuff_TypeInfo";

export class _ArtifactType_stuff_Validators extends IdEntityValidators {

    constructor(type: _ArtifactType_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArtifactType_stuff_TypeInfo;
    }

    validateUom(val: UomRow) {
    }

    validateParent(val: ArtifactType) {
    }

    validateCategory(val: ArtifactCategory) {
    }

}

export default _ArtifactType_stuff_Validators;
