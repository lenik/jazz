import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Post from "./Post";
import PostTagType from "./PostTagType";
import _PostTag_stuff_Validators from "./_PostTag_stuff_Validators";

export class _PostTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_tag";

    get name() { return "net.bodz.lily.schema.pub.PostTag"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_POST_ID = "post";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_POST_ID = 19;
    static N_TAG_ID = 10;

    validators = new _PostTag_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

            tag: property({ type: PostTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
            tagId: property({ type: INT, nullable: false, precision: 10 }),

            post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    constructor() {
        super();
    }

}

export default _PostTag_stuff_TypeInfo;
