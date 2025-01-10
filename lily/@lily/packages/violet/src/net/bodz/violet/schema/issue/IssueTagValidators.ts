import { ValidateResult } from "skel01-core/src/ui/types";

import type IssueTagTypeInfo from "./IssueTagTypeInfo";
import _IssueTag_stuff_Validators from "./_IssueTag_stuff_Validators";

export class IssueTagValidators extends _IssueTag_stuff_Validators {

    constructor(type: IssueTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssueTagTypeInfo;
    }

}

export default IssueTagValidators;
