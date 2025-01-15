import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoNodeValidators from "lily-basic/src/net/bodz/lily/concrete/CoNodeValidators";

import type Artifact from "../art/Artifact";
import type ArtifactCategory from "../art/ArtifactCategory";
import type Region from "./Region";
import type RegionCategory from "./RegionCategory";
import type RegionLevel from "./RegionLevel";
import type RegionTag from "./RegionTag";
import type _Region_stuff_TypeInfo from "./_Region_stuff_TypeInfo";

export class _Region_stuff_Validators extends CoNodeValidators {

    constructor(type: _Region_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _Region_stuff_TypeInfo;
    }

    validateCode(val: string) {
    }

    validatePath(val: string) {
    }

    validateHeight(val: int) {
    }

    validateForCat(val: ArtifactCategory) {
    }

    validateProto(val: Region) {
    }

    validateTag(val: RegionTag) {
    }

    validateCategory(val: RegionCategory) {
    }

    validateLevel(val: RegionLevel) {
    }

    validateArtifact(val: Artifact) {
    }

    validateMaterial(val: Artifact) {
    }

    validateForArt(val: Artifact) {
    }

}

export default _Region_stuff_Validators;
