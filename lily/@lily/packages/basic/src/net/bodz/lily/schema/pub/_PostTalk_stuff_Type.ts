
import type { CoTalkType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTalkType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { TypeParamType } from "../../meta/TypeParamType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _PostTalk_stuff_Type extends CoTalkType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "post_msg";

    name = "net.bodz.lily.schema.pub.PostTalk"
    icon = "fa-tag"

    static const FIELD_FORM_ARGUMENTS = "formargs";
    static const FIELD_POST_ID = "post";

    static const N_FORM_ARGUMENTS = 2147483647;
    static const N_POST_ID = 19;

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: validators.validate_formArguments }),

        post: property({ type: "net.bodz.lily.schema.pub.Post", nullable: false, validator: validators.validate_post }),
        postId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_postId }),
    }

    constructor() {
        super();
        this.declare(_PostTalk_stuff_Type.declaredProperty);
    }

}
