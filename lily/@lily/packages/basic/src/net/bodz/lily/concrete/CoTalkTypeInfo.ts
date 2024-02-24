import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoTalkValidators from './CoTalkValidators';

export class CoTalkTypeInfo extends CoMessageTypeInfo {

    name = "net.bodz.lily.concrete.CoTalk"
    icon = "fa-comments"
    label = "Concrete Talk"
    description = "Replied messages."

    validators = new CoTalkValidators();

    declaredProperty: EntityPropertyMap = {
        parent: property({ type: 'any', icon: "far-tree", }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoTalkTypeInfo;