import { LIST } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import Group from "./Group";
import GroupValidators from "./GroupValidators";
import { User_TYPE } from "./UserTypeInfo";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class GroupTypeInfo extends _Group_stuff_TypeInfo {

    readonly validators = new GroupValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.Group"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Group"; }

    override create() {
        return new Group();
    }

    override preamble() {
        super.preamble();
        this.declare({
            children: property({ type: LIST(this), 
                description: "子用户组列表", 
                validator: this.validators.validateChildren }),
            users: property({ type: LIST(User_TYPE), 
                description: "用户", 
                validator: this.validators.validateUsers }),
        });
    }

    static readonly INSTANCE = new GroupTypeInfo();

}

export default GroupTypeInfo;

export const Group_TYPE = GroupTypeInfo.INSTANCE;
