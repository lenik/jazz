import type { int, long } from "@skeljs/core/src/lang/basetype";

import BackrefRecord from "../../concrete/BackrefRecord";
import type ExternalSite from "../inet/ExternalSite";
import type Post from "./Post";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class _PostBackref_stuff extends BackrefRecord {

    static _typeInfo: _PostBackref_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PostBackref_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

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

export default _PostBackref_stuff;
