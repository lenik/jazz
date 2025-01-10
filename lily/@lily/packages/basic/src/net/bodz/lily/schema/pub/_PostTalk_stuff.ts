import type { long } from "skel01-core/src/lang/basetype";

import CoTalk from "../../concrete/CoTalk";
import type Post from "./Post";

export class _PostTalk_stuff<this_t> extends CoTalk<this_t> {

    formArguments?: string;

    post: Post;
    postId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostTalk_stuff;
