import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import _PriorityDef_stuff_Validators from "./_PriorityDef_stuff_Validators";

export class _PriorityDef_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_priority";

    readonly validators = new _PriorityDef_stuff_Validators(this);

    constructor(selfType: any) {
        super(selfType);
    }

    get name() { return "net.bodz.lily.schema.meta.PriorityDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Priority"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PriorityDef_stuff_TypeInfo;
