import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoTalkValidators from './CoTalkValidators';

export class CoTalkTypeInfo extends CoMessageTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoTalk"; }
    get icon() { return "fa-comments"; }
    get label() { return "Concrete Talk"; }
    get description() { return "Replied messages."; }

    validators = new CoTalkValidators(this);

    declaredProperty: EntityPropertyMap = {
        parent: property({ type: 'any', icon: "far-tree", }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoTalkTypeInfo;