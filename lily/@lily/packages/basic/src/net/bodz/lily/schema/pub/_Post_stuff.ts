
import type { CoMessage } from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessage";
import type { integer, long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Object } from "../../../../../java/lang/Object";
import type { Post } from "./Post";
import type { PostCategory } from "./PostCategory";
import type { _Post_stuff_Type } from "./_Post_stuff_Type";

export class _Post_stuff extends CoMessage<long> {
    static TYPE = new _Post_stuff_Type();

    formArguments?: string;
    favCount: int;
    voteCount: int;
    hateCount: int;
    messageCount: int;
    plugins?: Object;

    parent?: Post;
    parentId?: long;

    category?: PostCategory;
    categoryId?: integer;

    constructor(o: any) {
        super(o);
    }
}
