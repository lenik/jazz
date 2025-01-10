import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type IssueTypeInfo from "./IssueTypeInfo";
import _Issue_stuff_Validators from "./_Issue_stuff_Validators";

export class IssueValidators extends _Issue_stuff_Validators {

    constructor(type: IssueTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueTypeInfo;
    }

}

export default IssueValidators;
