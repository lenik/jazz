import type { int, long } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type Article from "./Article";
import type ArticleTagType from "./ArticleTagType";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class _ArticleTag_stuff extends IdEntity<int> {

    static _typeInfo: _ArticleTag_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArticleTag_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    tag: ArticleTagType;
    tagId: int;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTag_stuff;
