import type { integer, long } from "@skeljs/core/src/lang/type";
import BackrefRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ExternalSite } from "../inet/ExternalSite";
import { Post } from "./Post";
import PostBackrefValidators from "./PostBackrefValidators";

export class _PostBackref_stuff_Type extends BackrefRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_backref";

    name = "net.bodz.lily.schema.pub.PostBackref"
    icon = "fa-tag"

    static FIELD_POST_ID = "post";
    static FIELD_SITE_ID = "site";
    static FIELD_KEY = "key";
    static FIELD_VALUE = "value";

    static N_POST_ID = 19;
    static N_SITE_ID = 10;
    static N_KEY = 30;
    static N_VALUE = 10;

    static validators = new PostBackrefValidators();

    static declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: this.validators.validateKey }),
        value: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateValue }),

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),

        site: property({ type: ExternalSite.TYPE, nullable: false, validator: this.validators.validateSite }),
        siteId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PostBackref_stuff_Type.declaredProperty);
    }

}

export default _PostBackref_stuff_Type;
