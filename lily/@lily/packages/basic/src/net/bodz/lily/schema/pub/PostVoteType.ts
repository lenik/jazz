
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostVote_stuff_Type } from "./_PostVote_stuff_Type";

// Type Info

export class PostVoteType extends _PostVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostVote"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostVoteType.declaredProperty);
    }

}
