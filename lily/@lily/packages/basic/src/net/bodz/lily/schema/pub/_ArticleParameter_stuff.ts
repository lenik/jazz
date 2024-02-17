
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer, long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Article } from "./Article";
import type { ArticleParameterType } from "./ArticleParameterType";
import type { _ArticleParameter_stuff_Type } from "./_ArticleParameter_stuff_Type";

export class _ArticleParameter_stuff extends CoEntity<Integer> {
    static TYPE = new _ArticleParameter_stuff_Type();

    id: int;
    ival?: integer;
    fval?: number;
    sval?: string;

    article: Article;
    articleId: long;

    parameter: ArticleParameterType;
    parameterId: int;

    constructor(o: any) {
        super(o);
    }
}
