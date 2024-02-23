import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecordTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _PostTalkVote_stuff_Validators from "./_PostTalkVote_stuff_Validators";

export class _PostTalkVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg_vote";

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    static validators = new _PostTalkVote_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: net.bodz.lily.schema.pub.PostTalkTypeInfo, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostTalkVote_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostTalkVote_stuff_TypeInfo;
