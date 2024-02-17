
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Article } from "./Article";
import type { ArticleTagType } from "./ArticleTagType";
import type { _ArticleTag_stuff_Type } from "./_ArticleTag_stuff_Type";

export class _ArticleTag_stuff extends CoEntity<Integer> {
    static TYPE = new _ArticleTag_stuff_Type();

    id: int;

    tag: ArticleTagType;
    tagId: int;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}
