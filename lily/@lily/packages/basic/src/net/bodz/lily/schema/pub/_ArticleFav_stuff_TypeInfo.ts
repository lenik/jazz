import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import Article from "./Article";
import _ArticleFav_stuff_Validators from "./_ArticleFav_stuff_Validators";

export class _ArticleFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_fav";

    get name() { return "net.bodz.lily.schema.pub.ArticleFav"; }
    get icon() { return "fa-tag"; }

    static FIELD_ARTICLE_ID = "article";

    static N_ARTICLE_ID = 19;

    validators = new _ArticleFav_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({

            article: property({ type: Article.TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ArticleFav_stuff_TypeInfo;
