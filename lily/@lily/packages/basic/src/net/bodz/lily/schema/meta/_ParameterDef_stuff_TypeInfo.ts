import AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import _ParameterDef_stuff_Validators from "./_ParameterDef_stuff_Validators";

export class _ParameterDef_stuff_TypeInfo extends AbstractDefinitionTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parm";

    readonly validators = new _ParameterDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.ParameterDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _ParameterDef_stuff_TypeInfo;

export const _ParameterDef_stuff_TYPE = _ParameterDef_stuff_TypeInfo.INSTANCE;
