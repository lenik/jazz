import { ValidateResult } from "skel01-core/src/ui/types";

import FavRecordValidators from "../../concrete/FavRecordValidators";
import type Article from "./Article";
import type _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class _ArticleFav_stuff_Validators extends FavRecordValidators {

    constructor(type: _ArticleFav_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _ArticleFav_stuff_TypeInfo;
    }

    validateArticle(val: Article) {
    }

}

export default _ArticleFav_stuff_Validators;
