import type { integer, long } from "@skeljs/core/src/lang/type";

import VoteRecord from "../../concrete/VoteRecord";
import type Post from "./Post";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class _PostVote_stuff extends VoteRecord {
    static TYPE = new _PostVote_stuff_TypeInfo();

    voteScore: integer;

    parent: Post;
    parentId: long;

    constructor(o: any) {
        super(o);
    }
}

export default _PostVote_stuff;
