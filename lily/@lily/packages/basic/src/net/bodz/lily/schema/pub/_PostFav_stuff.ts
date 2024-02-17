
import type { FavRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/FavRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Post } from "./Post";
import type { _PostFav_stuff_Type } from "./_PostFav_stuff_Type";

export class _PostFav_stuff extends FavRecord {
    static TYPE = new _PostFav_stuff_Type();


    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}
