import type { integer, long } from "@skeljs/core/src/lang/type";

import CoMessage from "../../concrete/CoMessage";
import type ArticleCategory from "./ArticleCategory";
import _Article_stuff_TypeInfo from "./_Article_stuff_TypeInfo";

export class _Article_stuff extends CoMessage<long> {
    static TYPE = new _Article_stuff_TypeInfo();

    formArguments?: string;
    favCount: integer;
    voteCount: integer;
    hateCount: integer;
    messageCount: integer;
    plugins?: any;

    category?: ArticleCategory;
    categoryId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Article_stuff;
