
import type { FavRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Article } from "./Article";
import type { _ArticleFav_stuff_Type } from "./_ArticleFav_stuff_Type";

export class _ArticleFav_stuff extends FavRecord {
    static TYPE = new _ArticleFav_stuff_Type();


    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}
