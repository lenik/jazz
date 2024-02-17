
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";
import type { _PostTalk_stuff_Type } from "./_PostTalk_stuff_Type";

// Type Info

export class PostTalkType extends _PostTalk_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkType.declaredProperty);
    }

}
