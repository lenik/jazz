import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoMessageTypeInfo from "../../concrete/CoMessageTypeInfo";
import { PostCategory_TYPE } from "./PostCategoryTypeInfo";
import { Post_TYPE } from "./PostTypeInfo";
import _Post_stuff_Validators from "./_Post_stuff_Validators";

export class _Post_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_FAV_COUNT = "nfav";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_HATE_COUNT = "nhate";
    static readonly FIELD_MESSAGE_COUNT = "nmsg";
    static readonly FIELD_PLUGINS = "plugins";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_PARENT_ID = 19;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_FAV_COUNT = 10;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_HATE_COUNT = 10;
    static readonly N_MESSAGE_COUNT = 10;
    static readonly N_PLUGINS = 2147483647;

    readonly validators = new _Post_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.lily.schema.pub.Post"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            favCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateFavCount }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            hateCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHateCount }),
            messageCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateMessageCount }),
            plugins: property({ type: JSON_VARIANT, validator: this.validators.validatePlugins }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, precision: 19 }),

            category: property({ type: PostCategory_TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Post_stuff_TypeInfo();

}

export default _Post_stuff_TypeInfo;

export const _Post_stuff_TYPE = _Post_stuff_TypeInfo.INSTANCE;
