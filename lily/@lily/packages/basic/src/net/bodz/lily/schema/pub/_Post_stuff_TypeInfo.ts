import type { integer, long } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoMessageTypeInfo from "../../concrete/CoMessageTypeInfo";
import PostCategoryTypeInfo from "./PostCategoryTypeInfo";
import PostTypeInfo from "./PostTypeInfo";
import _Post_stuff_Validators from "./_Post_stuff_Validators";

export class _Post_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post";

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

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
        formArguments: property({ type: "string", validator: this.validators.validateFormArguments }),
        favCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateFavCount }),
        voteCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
        hateCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateHateCount }),
        messageCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateMessageCount }),
        plugins: property({ type: "any", validator: this.validators.validatePlugins }),

        parent: property({ type: PostTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "long", precision: 19 }),

        category: property({ type: PostCategoryTypeInfo, validator: this.validators.validateCategory }),
        categoryId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Post_stuff_TypeInfo;
