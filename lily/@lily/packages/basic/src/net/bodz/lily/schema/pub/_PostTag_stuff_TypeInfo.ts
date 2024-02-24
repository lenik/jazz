import type { integer, long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import PostTagTypeTypeInfo from "./PostTagTypeTypeInfo";
import PostTypeInfo from "./PostTypeInfo";
import _PostTag_stuff_Validators from "./_PostTag_stuff_Validators";

export class _PostTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_tag";

    name = "net.bodz.lily.schema.pub.PostTag"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_POST_ID = "post";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_POST_ID = 19;
    static N_TAG_ID = 10;

    validators = new _PostTag_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        tag: property({ type: PostTagTypeTypeInfo, nullable: false, validator: this.validators.validateTag }),
        tagId: property({ type: "integer", nullable: false, precision: 10 }),

        post: property({ type: PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostTag_stuff_TypeInfo;
