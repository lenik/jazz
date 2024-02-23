import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_Type from "./_PostTalk_stuff_Type";

// Type Info

export class PostTalkType extends _PostTalk_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static validators = new PostTalkValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkType.declaredProperty);
    }

}

export default PostTalk;
