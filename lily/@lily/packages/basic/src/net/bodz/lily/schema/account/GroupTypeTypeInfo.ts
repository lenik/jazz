import GroupType from "./GroupType";
import GroupTypeValidators from "./GroupTypeValidators";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class GroupTypeTypeInfo extends _GroupType_stuff_TypeInfo {

    readonly validators = new GroupTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.GroupType"; }
    get icon() { return "fa-tag"; }
    get description() { return "Group Type"; }

    override create() {
        return new GroupType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new GroupTypeTypeInfo();

}

export default GroupTypeTypeInfo;
