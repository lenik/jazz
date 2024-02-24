import type { long } from "@skeljs/core/src/lang/type";

import FavRecord from "../../concrete/FavRecord";
import type Article from "./Article";
import _ArticleFav_stuff_TypeInfo from "./_ArticleFav_stuff_TypeInfo";

export class _ArticleFav_stuff extends FavRecord {
    static TYPE = new _ArticleFav_stuff_TypeInfo();


    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleFav_stuff;
