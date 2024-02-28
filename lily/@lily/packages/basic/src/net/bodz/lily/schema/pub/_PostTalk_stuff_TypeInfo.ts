import { LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoTalkTypeInfo from "../../concrete/CoTalkTypeInfo";
import Post from "./Post";
import _PostTalk_stuff_Validators from "./_PostTalk_stuff_Validators";

export class _PostTalk_stuff_TypeInfo extends CoTalkTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg";

    get name() { return "net.bodz.lily.schema.pub.PostTalk"; }
    get icon() { return "fa-tag"; }

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_POST_ID = "post";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_POST_ID = 19;

    validators = new _PostTalk_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: LONG, nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostTalk_stuff_TypeInfo;
