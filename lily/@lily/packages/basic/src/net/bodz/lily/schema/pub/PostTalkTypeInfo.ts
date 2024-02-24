import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class PostTalkTypeInfo extends _PostTalk_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    validators = new PostTalkValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTalkTypeInfo;
