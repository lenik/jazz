
import type { CoMessageType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoMessageType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _Post_stuff_Type extends CoMessageType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "post";

    name = "net.bodz.lily.schema.pub.Post"
    icon = "fa-tag"

    static const FIELD_FORM_ARGUMENTS = "formargs";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_CATEGORY_ID = "cat";
    static const FIELD_FAV_COUNT = "nfav";
    static const FIELD_VOTE_COUNT = "nvote";
    static const FIELD_HATE_COUNT = "nhate";
    static const FIELD_MESSAGE_COUNT = "nmsg";
    static const FIELD_PLUGINS = "plugins";

    static const N_FORM_ARGUMENTS = 2147483647;
    static const N_PARENT_ID = 19;
    static const N_CATEGORY_ID = 10;
    static const N_FAV_COUNT = 10;
    static const N_VOTE_COUNT = 10;
    static const N_HATE_COUNT = 10;
    static const N_MESSAGE_COUNT = 10;
    static const N_PLUGINS = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        formArguments: property({ type: "string", validator: validators.validate_formArguments }),
        favCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_favCount }),
        voteCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_voteCount }),
        hateCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_hateCount }),
        messageCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_messageCount }),
        plugins: property({ type: "java.lang.Object", validator: validators.validate_plugins }),

        parent: property({ type: "net.bodz.lily.schema.pub.Post", validator: validators.validate_parent }),
        parentId: property({ type: "long", precision: 19, validator: validators.validate_parentId }),

        category: property({ type: "net.bodz.lily.schema.pub.PostCategory", validator: validators.validate_category }),
        categoryId: property({ type: "integer", precision: 10, validator: validators.validate_categoryId }),
    }

    constructor() {
        super();
        this.declare(_Post_stuff_Type.declaredProperty);
    }

}
