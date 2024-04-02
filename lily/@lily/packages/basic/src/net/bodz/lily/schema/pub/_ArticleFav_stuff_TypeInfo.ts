import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import FavRecordTypeInfo from "../../concrete/FavRecordTypeInfo";
import { Article_TYPE } from "./ArticleTypeInfo";
import _ArticleFav_stuff_Validators from "./_ArticleFav_stuff_Validators";

export class _ArticleFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_fav";

    static readonly FIELD_ARTICLE_ID = "article";

    static readonly N_ARTICLE_ID = 19;

    readonly validators = new _ArticleFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.ArticleFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            article: property({ type: Article_TYPE, nullable: false, validator: this.validators.validateArticle }),
            articleId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _ArticleFav_stuff_TypeInfo();

}

export default _ArticleFav_stuff_TypeInfo;

export const _ArticleFav_stuff_TYPE = _ArticleFav_stuff_TypeInfo.INSTANCE;
