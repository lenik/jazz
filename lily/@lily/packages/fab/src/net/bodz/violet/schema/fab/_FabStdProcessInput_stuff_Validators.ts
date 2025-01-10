import type { BigDecimal, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import IdEntityValidators from "@lily/basic/src/net/bodz/lily/concrete/IdEntityValidators";
import type Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import type ArtifactModel from "../art/ArtifactModel";
import type FabStdProcess from "./FabStdProcess";
import type _FabStdProcessInput_stuff_TypeInfo from "./_FabStdProcessInput_stuff_TypeInfo";

export class _FabStdProcessInput_stuff_Validators extends IdEntityValidators {

    constructor(type: _FabStdProcessInput_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _FabStdProcessInput_stuff_TypeInfo;
    }

    validateQuantity(val: BigDecimal) {
    }

    validateProcess(val: FabStdProcess) {
    }

    validateModel(val: ArtifactModel) {
    }

    validateArtifact(val: Artifact) {
    }

}

export default _FabStdProcessInput_stuff_Validators;
