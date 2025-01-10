import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoTalkValidators from './CoTalkValidators';
import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { UNDEFINED } from 'skel01-core/src/lang/baseinfo';

export class CoTalkTypeInfo extends CoMessageTypeInfo {

    readonly selfType: TypeInfo<any> = this;
    readonly validators = new CoTalkValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
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

    static readonly INSTANCE = new CoTalkTypeInfo(UNDEFINED);

}

export default CoTalkTypeInfo;
export const CoTalk_TYPE = CoTalkTypeInfo.INSTANCE;
