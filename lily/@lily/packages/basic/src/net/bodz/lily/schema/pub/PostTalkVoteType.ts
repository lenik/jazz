
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostTalkVote_stuff_Type } from "./_PostTalkVote_stuff_Type";

// Type Info

export class PostTalkVoteType extends _PostTalkVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkVoteType.declaredProperty);
    }

}
