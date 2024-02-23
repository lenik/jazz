import type { long } from "@skeljs/core/src/lang/type";
import CoTalk from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalk";

import { TypeParamType } from "../../meta/TypeParamType";
import Article from "./Article";
import _ArticleTalk_stuff_Type from "./_ArticleTalk_stuff_Type";

export class _ArticleTalk_stuff<this_t> extends CoTalk<this_t> {
    static TYPE = new _ArticleTalk_stuff_Type();

    formArguments?: string;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalk_stuff;
