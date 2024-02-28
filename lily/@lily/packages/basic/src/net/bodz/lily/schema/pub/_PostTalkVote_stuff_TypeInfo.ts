import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import VoteRecordTypeInfo from "../../concrete/VoteRecordTypeInfo";
import PostTalk from "./PostTalk";
import _PostTalkVote_stuff_Validators from "./_PostTalkVote_stuff_Validators";

export class _PostTalkVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg_vote";

    get name() { return "net.bodz.lily.schema.pub.PostTalkVote"; }
    get icon() { return "fa-tag"; }

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    validators = new _PostTalkVote_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: PostTalk.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: LONG, nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostTalkVote_stuff_TypeInfo;
