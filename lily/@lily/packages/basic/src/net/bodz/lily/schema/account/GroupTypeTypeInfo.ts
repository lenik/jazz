import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class GroupTypeTypeInfo extends _GroupType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.GroupType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Group Type"; }

    validators = new GroupTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default GroupTypeTypeInfo;
