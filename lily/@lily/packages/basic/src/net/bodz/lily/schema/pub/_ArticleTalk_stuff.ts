import type { long } from "@skeljs/core/src/lang/basetype";

import CoTalk from "../../concrete/CoTalk";
import type Article from "./Article";

export class _ArticleTalk_stuff<this_t> extends CoTalk<this_t> {

    formArguments?: string;

    article: Article;
    articleId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTalk_stuff;
