
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleParameter_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_parm";

    name = "net.bodz.lily.schema.pub.ArticleParameter"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_ARTICLE_ID = "article";
    static const FIELD_PARAMETER_ID = "parm";
    static const FIELD_IVAL = "ival";
    static const FIELD_FVAL = "fval";
    static const FIELD_SVAL = "sval";

    static const N_ID = 10;
    static const N_ARTICLE_ID = 19;
    static const N_PARAMETER_ID = 10;
    static const N_IVAL = 10;
    static const N_FVAL = 17;
    static const N_SVAL = 250;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        ival: property({ type: "integer", precision: 10, validator: validators.validate_ival }),
        fval: property({ type: "number", precision: 17, scale: 17, validator: validators.validate_fval }),
        sval: property({ type: "string", precision: 250, validator: validators.validate_sval }),

        article: property({ type: "net.bodz.lily.schema.pub.Article", nullable: false, validator: validators.validate_article }),
        articleId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_articleId }),

        parameter: property({ type: "net.bodz.lily.schema.pub.ArticleParameterType", nullable: false, validator: validators.validate_parameter }),
        parameterId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_parameterId }),
    }

    constructor() {
        super();
        this.declare(_ArticleParameter_stuff_Type.declaredProperty);
    }

}
