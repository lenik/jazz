
import type { CoTalk } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalk";
import type { long } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import type { Post } from "./Post";
import type { _PostTalk_stuff_Type } from "./_PostTalk_stuff_Type";

export class _PostTalk_stuff<this_t> extends CoTalk<this_t> {
    static TYPE = new _PostTalk_stuff_Type();

    formArguments?: string;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}
