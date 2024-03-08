import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoTalkValidators from './CoTalkValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';

export class CoTalkTypeInfo extends CoMessageTypeInfo {

    selfType: TypeInfo<any>

    get name() { return "net.bodz.lily.concrete.CoTalk"; }
    get icon() { return "fa-comments"; }
    get label() { return "Concrete Talk"; }
    get description() { return "Replied messages."; }

    validators = new CoTalkValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            parent: property({ type: this.selfType, icon: "far-tree", }),
        });
    }

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(idType);
        this.selfType = selfType;
    }

}

export default CoTalkTypeInfo;