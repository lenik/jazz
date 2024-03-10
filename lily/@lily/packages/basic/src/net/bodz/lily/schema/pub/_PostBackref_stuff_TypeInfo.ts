import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import BackrefRecordTypeInfo from "../../concrete/BackrefRecordTypeInfo";
import ExternalSite from "../inet/ExternalSite";
import Post from "./Post";
import _PostBackref_stuff_Validators from "./_PostBackref_stuff_Validators";

export class _PostBackref_stuff_TypeInfo extends BackrefRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_backref";

    static readonly FIELD_POST_ID = "post";
    static readonly FIELD_SITE_ID = "site";
    static readonly FIELD_KEY = "key";
    static readonly FIELD_VALUE = "value";

    static readonly N_POST_ID = 19;
    static readonly N_SITE_ID = 10;
    static readonly N_KEY = 30;
    static readonly N_VALUE = 10;

    readonly validators = new _PostBackref_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostBackref"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            key: property({ type: STRING, precision: 30, validator: this.validators.validateKey }),
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),

            site: property({ type: ExternalSite.TYPE, nullable: false, validator: this.validators.validateSite }),
            siteId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PostBackref_stuff_TypeInfo();

}

export default _PostBackref_stuff_TypeInfo;
