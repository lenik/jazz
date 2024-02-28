import type { long } from "@skeljs/core/src/lang/basetype";

import CoTalk from "../../concrete/CoTalk";
import type Article from "./Article";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class _ArticleTalk_stuff<this_t> extends CoTalk<this_t> {
    static _typeInfo: _ArticleTalk_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ArticleTalk_stuff_TypeInfo();
        return this._typeInfo;
    }

    formArguments?: string;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalk_stuff;
