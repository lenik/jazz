import type { integer, long } from "@skeljs/core/src/lang/type";
import BackrefRecord from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecord";

import ExternalSite from "../inet/ExternalSite";
import Article from "./Article";
import _ArticleBackref_stuff_Type from "./_ArticleBackref_stuff_Type";

export class _ArticleBackref_stuff extends BackrefRecord {
    static TYPE = new _ArticleBackref_stuff_Type();

    key?: string;
    value: integer;

    article: Article;
    articleId: long;

    site: ExternalSite;
    siteId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleBackref_stuff;
