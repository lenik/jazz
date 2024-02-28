import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import BackrefRecordValidators from "../../concrete/BackrefRecordValidators";
import type ExternalSite from "../inet/ExternalSite";
import type Article from "./Article";
import type _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

export class _ArticleBackref_stuff_Validators extends BackrefRecordValidators {

    constructor(type: _ArticleBackref_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleBackref_stuff_TypeInfo;
    }

    validateKey(val: string) {
    }

    validateValue(val: int) {
    }

    validateArticle(val: Article) {
    }

    validateSite(val: ExternalSite) {
    }

}

export default _ArticleBackref_stuff_Validators;
