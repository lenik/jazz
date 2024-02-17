
import type { BackrefRecordType } from "@skeljs/dba/src/net/bodz/lily/concrete/BackrefRecordType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleBackref_stuff_Type extends BackrefRecordType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_backref";

    name = "net.bodz.lily.schema.pub.ArticleBackref"
    icon = "fa-tag"

    static const FIELD_ARTICLE_ID = "article";
    static const FIELD_SITE_ID = "site";
    static const FIELD_KEY = "key";
    static const FIELD_VALUE = "value";

    static const N_ARTICLE_ID = 19;
    static const N_SITE_ID = 10;
    static const N_KEY = 30;
    static const N_VALUE = 10;

    static declaredProperty: EntityPropertyMap = {
        key: property({ type: "string", precision: 30, validator: validators.validate_key }),
        value: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_value }),

        article: property({ type: "net.bodz.lily.schema.pub.Article", nullable: false, validator: validators.validate_article }),
        articleId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_articleId }),

        site: property({ type: "net.bodz.lily.schema.inet.ExternalSite", nullable: false, validator: validators.validate_site }),
        siteId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_siteId }),
    }

    constructor() {
        super();
        this.declare(_ArticleBackref_stuff_Type.declaredProperty);
    }

}
