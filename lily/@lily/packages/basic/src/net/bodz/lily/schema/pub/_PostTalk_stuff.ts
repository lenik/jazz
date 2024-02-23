import type { long } from "@skeljs/core/src/lang/type";
import CoTalk from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalk";

import { TypeParamType } from "../../meta/TypeParamType";
import Post from "./Post";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class _PostTalk_stuff<this_t> extends CoTalk<this_t> {
    static TYPE = new _PostTalk_stuff_TypeInfo();

    formArguments?: string;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalk_stuff;
