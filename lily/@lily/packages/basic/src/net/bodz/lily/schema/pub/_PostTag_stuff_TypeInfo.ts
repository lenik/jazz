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

    static readonly FIELD_ID = "id";
    static readonly FIELD_POST_ID = "post";
    static readonly FIELD_TAG_ID = "tag";

    static readonly N_ID = 10;
    static readonly N_POST_ID = 19;
    static readonly N_TAG_ID = 10;

    readonly validators = new _PostTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTag"; }
    get icon() { return "fa-tag"; }

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

    static readonly INSTANCE = new _PostTag_stuff_TypeInfo();

}

export default _PostTag_stuff_TypeInfo;
