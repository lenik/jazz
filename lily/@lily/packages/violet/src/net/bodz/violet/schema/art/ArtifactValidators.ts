import type { double, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import type Uom from "@lily/basic/src/net/bodz/lily/schema/util/Uom";

import type ArtifactTypeInfo from "./ArtifactTypeInfo";
import type IArtifactExtras from "./IArtifactExtras";
import _Artifact_stuff_Validators from "./_Artifact_stuff_Validators";

export class ArtifactValidators extends _Artifact_stuff_Validators {

    constructor(type: ArtifactTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactTypeInfo;
    }

    validateConvMap(val: Map<Uom, double>) {
    }

    validateExtras(val: IArtifactExtras) {
    }

}

export default ArtifactValidators;
