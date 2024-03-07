import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
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

    declaredProperty: EntityPropertyMap = {
        parent: property({ type: this, icon: "far-tree", }),
    };

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(idType);
        this.selfType = selfType;
        this.declaredProperty.parent.type = selfType;
        this.declare(this.declaredProperty);
    }

}

export default CoTalkTypeInfo;