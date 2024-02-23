import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Post } from "./Post";
import PostVoteValidators from "./PostVoteValidators";

export class _PostVote_stuff_Type extends VoteRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_vote";

    name = "net.bodz.lily.schema.pub.PostVote"
    icon = "fa-tag"

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    static validators = new PostVoteValidators();

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: Post.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostVote_stuff_Type.declaredProperty);
    }

}

export default _PostVote_stuff_Type;
