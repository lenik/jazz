import AbstractDefinitionValidators from "./AbstractDefinitionValidators";
import CoCodeTypeInfo from "../../concrete/CoCodeTypeInfo";
import TypeInfo from "@skeljs/core/src/lang/TypeInfo";

export class AbstractDefinitionTypeInfo extends CoCodeTypeInfo {

    readonly validators = new AbstractDefinitionValidators(this);

    constructor(selfType: TypeInfo<any>) {
        super(selfType);
    }

    get name() { return "net.bodz.lily.schema.meta.AbstractDefinition"; }
    get icon() { return "fa-tag"; }
    get label() { return "Priority"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default AbstractDefinitionTypeInfo;
