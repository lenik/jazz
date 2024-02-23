import type { long } from "@skeljs/core/src/lang/type";
import CoTalkTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { TypeParamType } from "../../meta/TypeParamType";
import _PostTalk_stuff_Validators from "./_PostTalk_stuff_Validators";

export class _PostTalk_stuff_TypeInfo extends CoTalkTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg";

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_POST_ID = "post";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_POST_ID = 19;

    static validators = new _PostTalk_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: this.validators.validateFormArguments }),

        post: property({ type: net.bodz.lily.schema.pub.PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_PostTalk_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostTalk_stuff_TypeInfo;
