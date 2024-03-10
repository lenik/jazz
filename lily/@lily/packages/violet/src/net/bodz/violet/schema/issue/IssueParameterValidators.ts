import { ValidateResult } from "@skeljs/core/src/ui/types";

import type IssueParameterTypeInfo from "./IssueParameterTypeInfo";
import _IssueParameter_stuff_Validators from "./_IssueParameter_stuff_Validators";

export class IssueParameterValidators extends _IssueParameter_stuff_Validators {

    constructor(type: IssueParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueParameterTypeInfo;
    }

}

export default IssueParameterValidators;
