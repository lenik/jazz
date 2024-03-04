import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoMailValidators from './CoMailValidators';
import User from '../schema/account/User';

export class CoMailTypeInfo extends CoMessageTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoMail"; }
    get icon() { return "fa-envelop"; }
    get label() { return "Concrete Mail"; }
    get description() { return "Mail like message, includes recipients, timing, rich text content."; }

    validators = new CoMailValidators(this);

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