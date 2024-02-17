
import type { BackrefRecord } from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecord";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { ExternalSite } from "../inet/ExternalSite";
import type { Post } from "./Post";
import type { _PostBackref_stuff_Type } from "./_PostBackref_stuff_Type";

export class _PostBackref_stuff extends BackrefRecord {
    static TYPE = new _PostBackref_stuff_Type();

    key?: string;
    value: int;

    post: Post;
    postId: long;

    site: ExternalSite;
    siteId: int;

    constructor(o: any) {
        super(o);
    }
}
