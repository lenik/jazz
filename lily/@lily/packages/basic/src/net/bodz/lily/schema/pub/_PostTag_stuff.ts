import type { integer, long } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Post from "./Post";
import PostTagType from "./PostTagType";
import _PostTag_stuff_Type from "./_PostTag_stuff_Type";

export class _PostTag_stuff extends CoEntity<integer> {
    static TYPE = new _PostTag_stuff_Type();

    id: integer;

    tag: PostTagType;
    tagId: integer;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTag_stuff;
