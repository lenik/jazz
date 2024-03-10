import { DOUBLE, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Article from "./Article";
import ArticleParameterType from "./ArticleParameterType";
import _ArticleParameter_stuff_Validators from "./_ArticleParameter_stuff_Validators";

export class _ArticleParameter_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_parm";

    static readonly FIELD_ID = "id";
    static readonly FIELD_ARTICLE_ID = "article";
    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_IVAL = "ival";
    static readonly FIELD_FVAL = "fval";
    static readonly FIELD_SVAL = "sval";

    static readonly N_ID = 10;
    static readonly N_ARTICLE_ID = 19;
    static readonly N_PARAMETER_ID = 10;
    static readonly N_IVAL = 10;
    static readonly N_FVAL = 17;
    static readonly N_SVAL = 250;

    readonly validators = new _ArticleParameter_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            ival: property({ type: INT, precision: 10, validator: this.validators.validateIval }),
            fval: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateFval }),
            sval: property({ type: STRING, precision: 250, validator: this.validators.validateSval }),

            article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),

            parameter: property({ type: ArticleParameterType.TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _ArticleParameter_stuff_TypeInfo();

}

export default _ArticleParameter_stuff_TypeInfo;
