import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";

import CourseCategory from "./CourseCategory";
import _Course_stuff_Validators from "./_Course_stuff_Validators";

export class _Course_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "course";

    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_FAV_COUNT = "nfav";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_HATE_COUNT = "nhate";
    static readonly FIELD_CREDIT = "credit";
    static readonly FIELD_PLUGINS = "plugins";

    static readonly N_CATEGORY_ID = 10;
    static readonly N_FAV_COUNT = 10;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_HATE_COUNT = 10;
    static readonly N_CREDIT = 10;
    static readonly N_PLUGINS = 2147483647;

    readonly validators = new _Course_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.edu.Course"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            favCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateFavCount }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            hateCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHateCount }),
            credit: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateCredit }),
            plugins: property({ type: JSON_VARIANT, validator: this.validators.validatePlugins }),

            category: property({ type: CourseCategory.TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Course_stuff_TypeInfo();

}

export default _Course_stuff_TypeInfo;
