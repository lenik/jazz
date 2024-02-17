
import type { BackrefRecordType } from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _PostBackref_stuff_Type extends BackrefRecordType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "post_backref";

    name = "net.bodz.lily.schema.pub.PostBackref"
    icon = "fa-tag"

    static const FIELD_POST_ID = "post";
    static const FIELD_SITE_ID = "site";
    static const FIELD_KEY = "key";
    static const FIELD_VALUE = "value";

    static const N_POST_ID = 19;
    static const N_SITE_ID = 10;
    static const N_KEY = 30;
    static const N_VALUE = 10;

    static declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: validators.validate_key }),
        value: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_value }),

        post: property({ type: "net.bodz.lily.schema.pub.Post", nullable: false, validator: validators.validate_post }),
        postId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_postId }),

        site: property({ type: "net.bodz.lily.schema.inet.ExternalSite", nullable: false, validator: validators.validate_site }),
        siteId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_siteId }),
    }

    constructor() {
        super();
        this.declare(_PostBackref_stuff_Type.declaredProperty);
    }

}
