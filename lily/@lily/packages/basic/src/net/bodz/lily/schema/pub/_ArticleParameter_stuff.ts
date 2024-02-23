import type { double, integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Article from "./Article";
import ArticleParameterType from "./ArticleParameterType";
import _ArticleParameter_stuff_Type from "./_ArticleParameter_stuff_Type";

export class _ArticleParameter_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleParameter_stuff_Type();

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
