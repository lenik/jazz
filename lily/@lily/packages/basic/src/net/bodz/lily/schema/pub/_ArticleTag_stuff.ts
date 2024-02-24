import type { integer, long } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import type Article from "./Article";
import type ArticleTagType from "./ArticleTagType";
import _ArticleTag_stuff_TypeInfo from "./_ArticleTag_stuff_TypeInfo";

export class _ArticleTag_stuff extends CoEntity<integer> {
    static TYPE = new _ArticleTag_stuff_TypeInfo();

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
