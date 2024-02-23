import type { integer, long } from "@skeljs/core/src/lang/type";
import CoMessage from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessage";

import Post from "./Post";
import PostCategory from "./PostCategory";
import _Post_stuff_Type from "./_Post_stuff_Type";

export class _Post_stuff extends CoMessage<long> {
    static TYPE = new _Post_stuff_Type();

    formArguments?: string;
    favCount: integer;
    voteCount: integer;
    hateCount: integer;
    messageCount: integer;
    plugins?: any;

    parent?: Post;
    parentId?: long;

    category?: PostCategory;
    categoryId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Post_stuff;
