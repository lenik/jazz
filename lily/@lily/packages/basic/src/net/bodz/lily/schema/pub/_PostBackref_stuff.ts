import type { integer, long } from "@skeljs/core/src/lang/type";

import BackrefRecord from "../../concrete/BackrefRecord";
import type ExternalSite from "../inet/ExternalSite";
import type Post from "./Post";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class _PostBackref_stuff extends BackrefRecord {
    static TYPE = new _PostBackref_stuff_TypeInfo();

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
