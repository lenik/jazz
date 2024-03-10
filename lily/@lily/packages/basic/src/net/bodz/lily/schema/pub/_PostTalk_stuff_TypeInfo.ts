import { LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoTalkTypeInfo from "../../concrete/CoTalkTypeInfo";
import Post from "./Post";
import _PostTalk_stuff_Validators from "./_PostTalk_stuff_Validators";

export class _PostTalk_stuff_TypeInfo extends CoTalkTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_POST_ID = "post";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_POST_ID = 19;

    readonly validators = new _PostTalk_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTalk"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

}

export default _PostTalk_stuff_TypeInfo;
