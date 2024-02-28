import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoMessageTypeInfo from "../../concrete/CoMessageTypeInfo";
import Post from "./Post";
import PostCategory from "./PostCategory";
import _Post_stuff_Validators from "./_Post_stuff_Validators";

export class _Post_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post";

    get name() { return "net.bodz.lily.schema.pub.Post"; }
    get icon() { return "fa-tag"; }

    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_PARENT_ID = "parent";
    static FIELD_CATEGORY_ID = "cat";
    static FIELD_FAV_COUNT = "nfav";
    static FIELD_VOTE_COUNT = "nvote";
    static FIELD_HATE_COUNT = "nhate";
    static FIELD_MESSAGE_COUNT = "nmsg";
    static FIELD_PLUGINS = "plugins";

    static N_FORM_ARGUMENTS = 2147483647;
    static N_PARENT_ID = 19;
    static N_CATEGORY_ID = 10;
    static N_FAV_COUNT = 10;
    static N_VOTE_COUNT = 10;
    static N_HATE_COUNT = 10;
    static N_MESSAGE_COUNT = 10;
    static N_PLUGINS = 2147483647;

    validators = new _Post_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
        favCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateFavCount }),
        voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
        hateCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHateCount }),
        messageCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateMessageCount }),
        plugins: property({ type: JSON_VARIANT, validator: this.validators.validatePlugins }),

        parent: property({ type: this, validator: this.validators.validateParent }),
        parentId: property({ type: LONG, precision: 19 }),

        category: property({ type: PostCategory.TYPE, validator: this.validators.validateCategory }),
        categoryId: property({ type: INT, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Post_stuff_TypeInfo;
