
import type { CoTalk } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalk";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import type { Article } from "./Article";
import type { _ArticleTalk_stuff_Type } from "./_ArticleTalk_stuff_Type";

export class _ArticleTalk_stuff<this_t> extends CoTalk<this_t> {
    static TYPE = new _ArticleTalk_stuff_Type();

    formArguments?: string;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}
