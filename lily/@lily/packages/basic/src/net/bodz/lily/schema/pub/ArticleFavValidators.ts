import { ValidateResult } from "skel01-core/src/ui/types";

import type ArticleFavTypeInfo from "./ArticleFavTypeInfo";
import _ArticleFav_stuff_Validators from "./_ArticleFav_stuff_Validators";

export class ArticleFavValidators extends _ArticleFav_stuff_Validators {

    constructor(type: ArticleFavTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ArticleFavTypeInfo;
    }

}

export default ArticleFavValidators;
