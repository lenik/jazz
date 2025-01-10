import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import BackrefRecordValidators from "../../concrete/BackrefRecordValidators";
import type ExternalSite from "../inet/ExternalSite";
import type Post from "./Post";
import type _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class _PostBackref_stuff_Validators extends BackrefRecordValidators {

    constructor(type: _PostBackref_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _PostBackref_stuff_TypeInfo;
    }

    validateKey(val: string) {
    }

    validateValue(val: int) {
    }

    validatePost(val: Post) {
    }

    validateSite(val: ExternalSite) {
    }

}

export default _PostBackref_stuff_Validators;
