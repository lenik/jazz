import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import VoteRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordTypeInfo";

import { Artifact_TYPE } from "./ArtifactTypeInfo";
import _ArtifactVote_stuff_Validators from "./_ArtifactVote_stuff_Validators";

export class _ArtifactVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "art_vote";

    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_VOTE_SCORE = "votes";

    static readonly N_PARENT_ID = 10;
    static readonly N_VOTE_SCORE = 10;

    readonly validators = new _ArtifactVote_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.art.ArtifactVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

            parent: property({ type: Artifact_TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArtifactVote_stuff_TypeInfo();

}

export default _ArtifactVote_stuff_TypeInfo;

export const _ArtifactVote_stuff_TYPE = _ArtifactVote_stuff_TypeInfo.INSTANCE;
