import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Article from "./Article";
import ArticleTagType from "./ArticleTagType";
import _ArticleTag_stuff_Type from "./_ArticleTag_stuff_Type";

export class _ArticleTag_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleTag_stuff_Type();

    id: integer;

    tag: ArticleTagType;
    tagId: integer;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTag_stuff;
