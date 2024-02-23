import type { integer, long } from "@skeljs/core/src/lang/type";
import CoMessage from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessage";

import ArticleCategory from "./ArticleCategory";
import _Article_stuff_Type from "./_Article_stuff_Type";

export class _Article_stuff extends CoMessage<long> {
    static TYPE = new _Article_stuff_Type();

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
