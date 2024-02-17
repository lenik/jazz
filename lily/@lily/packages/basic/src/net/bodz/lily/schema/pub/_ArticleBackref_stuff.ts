
import type { BackrefRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { ExternalSite } from "../inet/ExternalSite";
import type { Article } from "./Article";
import type { _ArticleBackref_stuff_Type } from "./_ArticleBackref_stuff_Type";

export class _ArticleBackref_stuff extends BackrefRecord {
    static TYPE = new _ArticleBackref_stuff_Type();

    key?: string;
    value: int;

    article: Article;
    articleId: long;

    site: ExternalSite;
    siteId: int;

    constructor(o: any) {
        super(o);
    }
}
