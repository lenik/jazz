import type { integer, long } from "@skeljs/core/src/lang/type";
import BackrefRecordTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _PostBackref_stuff_Validators from "./_PostBackref_stuff_Validators";

export class _PostBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

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

    static validators = new _PostBackref_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: this.validators.validateKey }),
        value: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateValue }),

        post: property({ type: net.bodz.lily.schema.pub.PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),

        site: property({ type: net.bodz.lily.schema.inet.ExternalSiteTypeInfo, nullable: false, validator: this.validators.validateSite }),
        siteId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PostBackref_stuff_TypeInfo.declaredProperty);
    }

}

export default _PostBackref_stuff_TypeInfo;
