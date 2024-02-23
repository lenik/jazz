import type { long } from "@skeljs/core/src/lang/type";
import CoTalkType from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import { Post } from "./Post";
import PostTalkValidators from "./PostTalkValidators";

export class _PostTalk_stuff_Type extends CoTalkType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg";

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_POST_ID = "post";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_POST_ID = 19;

    static validators = new PostTalkValidators();

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: this.validators.validateFormArguments }),

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostTalk_stuff_Type.declaredProperty);
    }

}

export default _PostTalk_stuff_Type;
