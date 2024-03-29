
import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectValidators from './CoObjectValidators';
import StructRowTypeInfo from './StructRowTypeInfo';
import Group from '../schema/account/Group';
import User from '../schema/account/User';

export class CoObjectTypeInfo extends StructRowTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoObject"; }
    get icon() { return "far-gem"; }
    get label() { return "Concrete Object"; }
    get description() { return "A concrete object is a physical object that can be perceived through the senses, such as touch or sight. Examples of concrete objects include a table, a chair, a book, a car, or a tree. These objects have a tangible presence and can be easily identified and described."; }

    validators = new CoObjectValidators(this);

    declaredProperty: EntityPropertyMap = {
        id: property({ type: 'number', icon: 'far-key' }),
        name: property({ type: 'string', icon: 'far-id-card' }),
        properties: property({ type: 'any', icon: 'fab-pagelines' }),

        label: property({ type: 'string', icon: 'far-tag' }),
        description: property({ type: 'string', icon: 'far-sticky-note' }),
        icon: property({ type: 'string', icon: 'far-image' }),

        flags: property({ type: 'int', icon: 'far-toggle-on' }),
        priority: property({ type: 'int', icon: 'far-lightbulb' }),
        state: property({ type: 'string', icon: 'far-heart' }),

        ownerUser: property({ type: "User.TYPE", icon: 'far-user' }),
        ownerGroup: property({ type: "Group.TYPE", icon: 'far-users' }),

        acl: property({ type: 'int', icon: 'far-user-lock' }),
        accessMode: property({ type: 'int', icon: 'far-key' }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);

        import('../schema/account/User').then((a) => this.property.ownerUser.type = a.User.TYPE);
        import('../schema/account/Group').then((a) => this.property.ownerUser.type = a.Group.TYPE);
    }

}

export default CoObjectTypeInfo;