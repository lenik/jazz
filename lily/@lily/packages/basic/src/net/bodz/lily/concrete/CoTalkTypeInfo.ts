import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoTalkValidators from './CoTalkValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoTalkTypeInfo extends CoMessageTypeInfo {

    readonly selfType: TypeInfo<any>
    readonly validators = new CoTalkValidators(this);

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(idType);
        this.selfType = selfType;
    }

    get name() { return "net.bodz.lily.concrete.CoTalk"; }
    get icon() { return "fa-comments"; }
    get label() { return "Concrete Talk"; }
    get description() { return "Replied messages."; }

    override preamble() {
        super.preamble();
        this.declare({
            parent: property({ type: this.selfType, icon: "far-tree", }),
        });
    }

}

export default CoTalkTypeInfo;