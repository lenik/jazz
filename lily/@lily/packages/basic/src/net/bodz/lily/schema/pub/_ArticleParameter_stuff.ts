import type { double, integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Article from "./Article";
import ArticleParameterType from "./ArticleParameterType";
import _ArticleParameter_stuff_TypeInfo from "./_ArticleParameter_stuff_TypeInfo";

export class _ArticleParameter_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleParameter_stuff_TypeInfo();

    id: integer;
    ival?: integer;
    fval?: double;
    sval?: string;

    article: Article;
    articleId: long;

    parameter: ArticleParameterType;
    parameterId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleParameter_stuff;
