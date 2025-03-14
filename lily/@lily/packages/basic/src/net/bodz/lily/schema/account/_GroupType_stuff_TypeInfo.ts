import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _GroupType_stuff_Validators from "./_GroupType_stuff_Validators";

export class _GroupType_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "grouptype";

    static readonly FIELD_NAME = "name";
    static readonly FIELD_DUMMY = "dummy";

    static readonly N_NAME = 20;
    static readonly N_DUMMY = 10;

    readonly validators = new _GroupType_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.account.GroupType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Group Type"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, nullable: false, precision: 20, label: "Name", 
                description: "Group type name (unique)", 
                validator: this.validators.validateName }),
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

    static readonly INSTANCE = new _GroupType_stuff_TypeInfo();

}

export default _GroupType_stuff_TypeInfo;

export const _GroupType_stuff_TYPE = _GroupType_stuff_TypeInfo.INSTANCE;
