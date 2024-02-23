import type { double, integer, long } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _ArticleParameter_stuff_Validators from "./_ArticleParameter_stuff_Validators";

export class _ArticleParameter_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_parm";

    name = "net.bodz.lily.schema.pub.ArticleParameter"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_ARTICLE_ID = "article";
    static FIELD_PARAMETER_ID = "parm";
    static FIELD_IVAL = "ival";
    static FIELD_FVAL = "fval";
    static FIELD_SVAL = "sval";

    static N_ID = 10;
    static N_ARTICLE_ID = 19;
    static N_PARAMETER_ID = 10;
    static N_IVAL = 10;
    static N_FVAL = 17;
    static N_SVAL = 250;

    static validators = new _ArticleParameter_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        ival: property({ type: "integer", precision: 10, validator: this.validators.validateIval }),
        fval: property({ type: "double", precision: 17, scale: 17, validator: this.validators.validateFval }),
        sval: property({ type: "string", precision: 250, validator: this.validators.validateSval }),

        article: property({ type: net.bodz.lily.schema.pub.ArticleTypeInfo, nullable: false, validator: this.validators.validateArticle }),
        articleId: property({ type: "long", nullable: false, precision: 19 }),

        parameter: property({ type: net.bodz.lily.schema.pub.ArticleParameterTypeTypeInfo, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ArticleParameter_stuff_TypeInfo.declaredProperty);
    }

}

export default _ArticleParameter_stuff_TypeInfo;
