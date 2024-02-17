
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Post } from "./Post";
import type { PostTagType } from "./PostTagType";
import type { _PostTag_stuff_Type } from "./_PostTag_stuff_Type";

export class _PostTag_stuff extends CoEntity<Integer> {
    static TYPE = new _PostTag_stuff_Type();

    id: int;

    tag: PostTagType;
    tagId: int;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}
