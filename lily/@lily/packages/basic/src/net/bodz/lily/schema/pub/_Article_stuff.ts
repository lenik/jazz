
import type { CoMessage } from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessage";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Object } from "../../../../../java/lang/Object";
import type { ArticleCategory } from "./ArticleCategory";
import type { _Article_stuff_Type } from "./_Article_stuff_Type";

export class _Article_stuff extends CoMessage<long> {
    static TYPE = new _Article_stuff_Type();

    formArguments?: string;
    favCount: int;
    voteCount: int;
    hateCount: int;
    messageCount: int;
    plugins?: Object;

    category?: ArticleCategory;
    categoryId?: integer;

    constructor(o: any) {
        super(o);
    }
}
