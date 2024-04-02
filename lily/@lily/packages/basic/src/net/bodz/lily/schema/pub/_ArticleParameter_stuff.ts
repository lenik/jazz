import type { double, int, long } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type Article from "./Article";
import type ArticleParameterType from "./ArticleParameterType";
import _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class _ArticleParameter_stuff extends IdEntity<int> {

    static _typeInfo: _ArticleParameter_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ArticleParameter_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    ival?: int;
    fval?: double;
    sval?: string;

    article: Article;
    articleId: long;

    parameter: ArticleParameterType;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleParameter_stuff;
