import type { int, long } from "skel01-core/src/lang/basetype";

import BackrefRecord from "../../concrete/BackrefRecord";
import type ExternalSite from "../inet/ExternalSite";
import type Article from "./Article";
import _ArticleBackref_stuff_TypeInfo from "./_ArticleBackref_stuff_TypeInfo";

export class _ArticleBackref_stuff extends BackrefRecord {

    static _typeInfo: _ArticleBackref_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArticleBackref_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    key?: string;
    value: int;

    article: Article;
    articleId: long;

    site: ExternalSite;
    siteId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleBackref_stuff;
