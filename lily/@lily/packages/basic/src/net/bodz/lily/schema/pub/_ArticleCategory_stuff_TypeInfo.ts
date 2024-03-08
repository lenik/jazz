import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ArticleCategory from "./ArticleCategory";
import _ArticleCategory_stuff_Validators from "./_ArticleCategory_stuff_Validators";

export class _ArticleCategory_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articlecat";

    get name() { return "net.bodz.lily.schema.pub.ArticleCategory"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_NAME = "name";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";
    static FIELD_REF_COUNT = "nref";

    static N_ID = 10;
    static N_NAME = 30;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;
    static N_REF_COUNT = 10;

    validators = new _ArticleCategory_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            refCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRefCount }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ArticleCategory_stuff_TypeInfo;
