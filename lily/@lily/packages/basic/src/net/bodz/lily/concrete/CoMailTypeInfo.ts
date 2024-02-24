import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoMailValidators from './CoMailValidators';
import User from '../account/User';

export class CoMailTypeInfo extends CoMessageTypeInfo {

    name = "net.bodz.lily.concrete.CoMail"
    icon = "fa-envelop"
    label = "Concrete Mail"
    description = "Mail like message, includes recipients, timing, rich text content."

    validators = new CoMailValidators();

    declaredProperty: EntityPropertyMap = {
        recipient: property({ type: User, icon: "far-user", }),
        recipients: property({ type: "User[]", icon: "far-users", }),
        bcc: property({ type: "User[]", icon: "far-users", }),
        read: property({ type: 'boolean', icon: "far-eye", }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoMailTypeInfo;