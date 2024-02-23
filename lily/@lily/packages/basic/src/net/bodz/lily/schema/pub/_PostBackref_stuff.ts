import type { integer, long } from "@skeljs/core/src/lang/type";
import BackrefRecord from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecord";

import ExternalSite from "../inet/ExternalSite";
import Post from "./Post";
import _PostBackref_stuff_Type from "./_PostBackref_stuff_Type";

export class _PostBackref_stuff extends BackrefRecord {
    static TYPE = new _PostBackref_stuff_Type();

    key?: string;
    value: integer;

    post: Post;
    postId: long;

    site: ExternalSite;
    siteId: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _PostBackref_stuff;
