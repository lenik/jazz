import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import type IValidControl from "lily-basic/src/net/bodz/lily/concrete/util/IValidControl";
import type ArtifactModelTypeInfo from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";
import type FabCost from "lily-violet/src/net/bodz/violet/schema/art/FabCost";
import _ArtifactModel_stuff_Validators from "lily-violet/src/net/bodz/violet/schema/art/_ArtifactModel_stuff_Validators";

export class ArtifactModelValidators extends _ArtifactModel_stuff_Validators {

    constructor(type: ArtifactModelTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArtifactModelTypeInfo;
    }

    validateCost(val: FabCost) {
    }

    validateModelName(val: string) {
    }

    validateValidControl(val: IValidControl) {
    }

}

export default ArtifactModelValidators;
