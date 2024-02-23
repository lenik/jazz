import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

// Type Info

export class PostTalkTypeInfo extends _PostTalk_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static validators = new PostTalkValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkTypeInfo.declaredProperty);
    }

}

export default PostTalk;
