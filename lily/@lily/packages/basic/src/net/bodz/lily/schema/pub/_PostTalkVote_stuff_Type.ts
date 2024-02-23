import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { PostTalk } from "./PostTalk";
import PostTalkVoteValidators from "./PostTalkVoteValidators";

export class _PostTalkVote_stuff_Type extends VoteRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg_vote";

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    static validators = new PostTalkVoteValidators();

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: PostTalk.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostTalkVote_stuff_Type.declaredProperty);
    }

}

export default _PostTalkVote_stuff_Type;
