import type { long } from "@skeljs/core/src/lang/basetype";

import FavRecord from "../../concrete/FavRecord";
import type Article from "./Article";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class _ArticleFav_stuff extends FavRecord {
    static _typeInfo: _ArticleFav_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ArticleFav_stuff_TypeInfo();
        return this._typeInfo;
    }


    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleFav_stuff;
