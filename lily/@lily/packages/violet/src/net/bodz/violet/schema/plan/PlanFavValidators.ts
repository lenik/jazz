import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanFavTypeInfo from "./PlanFavTypeInfo";
import _PlanFav_stuff_Validators from "./_PlanFav_stuff_Validators";

export class PlanFavValidators extends _PlanFav_stuff_Validators {

    constructor(type: PlanFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanFavTypeInfo;
    }

}

export default PlanFavValidators;
