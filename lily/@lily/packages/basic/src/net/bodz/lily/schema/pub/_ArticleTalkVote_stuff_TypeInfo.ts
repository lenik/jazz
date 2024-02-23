import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecordTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _ArticleTalkVote_stuff_Validators from "./_ArticleTalkVote_stuff_Validators";

export class _ArticleTalkVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_msg_vote";

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    static validators = new _ArticleTalkVote_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: net.bodz.lily.schema.pub.ArticleTalkTypeInfo, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleTalkVote_stuff_TypeInfo.declaredProperty);
    }

}

export default _ArticleTalkVote_stuff_TypeInfo;
