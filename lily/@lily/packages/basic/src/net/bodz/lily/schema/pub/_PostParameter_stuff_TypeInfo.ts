import type { double, int, long } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import PostParameterTypeTypeInfo from "./PostParameterTypeTypeInfo";
import PostTypeInfo from "./PostTypeInfo";
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
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        ival: property({ type: "int", precision: 10, validator: this.validators.validateIval }),
        fval: property({ type: "double", precision: 17, scale: 17, validator: this.validators.validateFval }),
        sval: property({ type: "string", precision: 250, validator: this.validators.validateSval }),

        post: property({ type: PostTypeInfo, nullable: false, validator: this.validators.validatePost }),
        postId: property({ type: "long", nullable: false, precision: 19 }),

        parameter: property({ type: PostParameterTypeTypeInfo, nullable: false, validator: this.validators.validateParameter }),
        parameterId: property({ type: "int", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PostParameter_stuff_TypeInfo;
