import type { long } from "@skeljs/core/src/lang/type";

import CoTalk from "../../concrete/CoTalk";
import type Article from "./Article";
import _ArticleTalk_stuff_TypeInfo from "./_ArticleTalk_stuff_TypeInfo";

export class _ArticleTalk_stuff<this_t> extends CoTalk<this_t> {
    static TYPE = new _ArticleTalk_stuff_TypeInfo();

    formArguments?: string;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalk_stuff;
