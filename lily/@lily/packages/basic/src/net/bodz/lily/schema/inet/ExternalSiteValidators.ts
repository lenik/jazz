import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type ExternalSiteTypeInfo from "./ExternalSiteTypeInfo";
import _ExternalSite_stuff_Validators from "./_ExternalSite_stuff_Validators";

export class ExternalSiteValidators extends _ExternalSite_stuff_Validators {

    constructor(type: ExternalSiteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ExternalSiteTypeInfo;
    }

}

export default ExternalSiteValidators;
