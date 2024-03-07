import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import CoObjectValidators from './CoObjectValidators';
import StructRowTypeInfo from './StructRowTypeInfo';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT, STRING } from '@skeljs/core/src/lang/baseinfo';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import Group from '../schema/account/Group';
import User from '../schema/account/User';

export class CoObjectTypeInfo extends StructRowTypeInfo {

    idType: TypeInfo<any>

    get name() { return "net.bodz.lily.concrete.CoObject"; }
    get icon() { return "far-gem"; }
    get label() { return "Concrete Object"; }
    get description() { return "A concrete object is a physical object that can be perceived through the senses, such as touch or sight. Examples of concrete objects include a table, a chair, a book, a car, or a tree. These objects have a tangible presence and can be easily identified and described."; }

    validators = new CoObjectValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: property({ type: INT, icon: 'far-key' }),
        name: property({ type: STRING, icon: 'far-id-card' }),
        properties: property({ type: JSON_VARIANT, icon: 'fab-pagelines' }),

        label: property({ type: STRING, icon: 'far-tag' }),
        description: property({ type: STRING, icon: 'far-sticky-note' }),
        icon: property({ type: STRING, icon: 'far-image' }),

        flags: property({ type: INT, icon: 'far-toggle-on' }),
        priority: property({ type: INT, icon: 'far-lightbulb' }),
        state: property({ type: STRING, icon: 'far-heart' }),

        ownerUser: property({ type: User.TYPE, icon: 'far-user' }),
        ownerGroup: property({ type: Group.TYPE, icon: 'far-users' }),

        acl: property({ type: INT, icon: 'far-user-lock' }),
        accessMode: property({ type: INT, icon: 'far-key' }),
    };

    constructor() {
    // constructor(idType: TypeInfo<any>) {
        super();
        // this.idType = idType;
        this.declare(this.declaredProperty);

        import('../schema/account/User').then((a) => this.property.ownerUser.type = a.User.TYPE);
        import('../schema/account/Group').then((a) => this.property.ownerUser.type = a.Group.TYPE);
    }

}

export default CoObjectTypeInfo;