import type { double, integer, long } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Post } from "./Post";
import { PostParameterType } from "./PostParameterType";
import PostParameterValidators from "./PostParameterValidators";

export class _PostParameter_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_parm";

    name = "net.bodz.lily.schema.pub.PostParameter"
    icon = "fa-tag"

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

    static validators = new PostParameterValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        ival: property({ type: "integer", precision: 10, validator: this.validators.validateIval }),
        fval: property({ type: "double", precision: 17, scale: 17, validator: this.validators.validateFval }),
        sval: property({ type: "string", precision: 250, validator: this.validators.validateSval }),

        post: property({ type: Post.TYPE, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),

        parameter: property({ type: PostParameterType.TYPE, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PostParameter_stuff_Type.declaredProperty);
    }

}

export default _PostParameter_stuff_Type;
