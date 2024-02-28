import { DOUBLE, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Post from "./Post";
import PostParameterType from "./PostParameterType";
import _PostParameter_stuff_Validators from "./_PostParameter_stuff_Validators";

export class _PostParameter_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_parm";

    get name() { return "net.bodz.lily.schema.pub.PostParameter"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_POST_ID = "post";
    static FIELD_PARAMETER_ID = "parm";
    static FIELD_IVAL = "ival";
    static FIELD_FVAL = "fval";
    static FIELD_SVAL = "sval";

    static N_ID = 10;
    static N_POST_ID = 19;
    static N_PARAMETER_ID = 10;
    static N_IVAL = 10;
    static N_FVAL = 17;
    static N_SVAL = 250;

    validators = new _PostParameter_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        ival: property({ type: INT, precision: 10, validator: this.validators.validateIval }),
        fval: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateFval }),
        sval: property({ type: STRING, precision: 250, validator: this.validators.validateSval }),

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: LONG, nullable: false, precision: 19 }),

        parameter: property({ type: PostParameterType.TYPE, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostParameter_stuff_TypeInfo;
