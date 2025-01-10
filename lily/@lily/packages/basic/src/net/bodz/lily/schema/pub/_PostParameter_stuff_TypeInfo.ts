import { DOUBLE, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { PostParameterType_TYPE } from "./PostParameterTypeTypeInfo";
import { Post_TYPE } from "./PostTypeInfo";
import _PostParameter_stuff_Validators from "./_PostParameter_stuff_Validators";

export class _PostParameter_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_parm";

    static readonly FIELD_POST_ID = "post";
    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_IVAL = "ival";
    static readonly FIELD_FVAL = "fval";
    static readonly FIELD_SVAL = "sval";

    static readonly N_POST_ID = 19;
    static readonly N_PARAMETER_ID = 10;
    static readonly N_IVAL = 10;
    static readonly N_FVAL = 17;
    static readonly N_SVAL = 250;

    readonly validators = new _PostParameter_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.pub.PostParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            ival: property({ type: INT, precision: 10, validator: this.validators.validateIval }),
            fval: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateFval }),
            sval: property({ type: STRING, precision: 250, validator: this.validators.validateSval }),

            post: property({ type: Post_TYPE, nullable: false, validator: this.validators.validatePost }),
            postId: property({ type: LONG, nullable: false, precision: 19 }),

            parameter: property({ type: PostParameterType_TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PostParameter_stuff_TypeInfo();

}

export default _PostParameter_stuff_TypeInfo;

export const _PostParameter_stuff_TYPE = _PostParameter_stuff_TypeInfo.INSTANCE;
