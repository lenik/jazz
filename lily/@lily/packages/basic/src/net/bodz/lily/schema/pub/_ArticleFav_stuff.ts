import type { long } from "@skeljs/core/src/lang/type";
import FavRecord from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecord";

import Article from "./Article";
import _ArticleFav_stuff_Type from "./_ArticleFav_stuff_Type";

export class _ArticleFav_stuff extends FavRecord {
    static TYPE = new _ArticleFav_stuff_Type();


    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleFav_stuff;
