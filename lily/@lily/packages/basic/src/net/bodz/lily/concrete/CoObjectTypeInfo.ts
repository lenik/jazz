import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectValidators from './CoObjectValidators';
import StructRowTypeInfo from './StructRowTypeInfo';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT, STRING, UNDEFINED } from '@skeljs/core/src/lang/baseinfo';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import Group from '../schema/account/Group';
import User from '../schema/account/User';

class IconType extends TypeInfo<string> {

    get name(): string {
        throw 'Icon'
    }

    parse(s: string): string {
        return s;
    }
    format(val: string): string {
        return val;
    }

    renderHtml(val: any, context: any): string | HTMLElement | undefined {
        return "<b>Ops</b>";
    }

    static readonly INSTANCE = new IconType();

}

export class CoObjectTypeInfo extends StructRowTypeInfo {

    readonly idType: TypeInfo<any>
    readonly validators = new CoObjectValidators(this);

    constructor(idType: TypeInfo<any>) {
        super();
        this.idType = idType;

        import('../schema/account/User').then((a) => this.property.ownerUser.type = a.User.TYPE);
        import('../schema/account/Group').then((a) => this.property.ownerUser.type = a.Group.TYPE);
    }

    get name() { return "net.bodz.lily.concrete.CoObject"; }
    get icon() { return "far-gem"; }
    get label() { return "Concrete Object"; }
    get description() { return "A concrete object is a physical object that can be perceived through the senses, such as touch or sight. Examples of concrete objects include a table, a chair, a book, a car, or a tree. These objects have a tangible presence and can be easily identified and described."; }

    override preamble() {
        super.preamble();
        this.declare({
            id: property({ type: this.idType, icon: 'far-key' }),
            name: property({ type: STRING, icon: 'far-id-card' }),
            properties: property({ type: JSON_VARIANT, icon: 'fab-pagelines' }),

            label: property({ type: STRING, icon: 'far-tag' }),
            description: property({ type: STRING, icon: 'far-sticky-note' }),
            icon: property({ type: IconType.INSTANCE, icon: 'far-image' }),

            flags: property({ type: INT, icon: 'far-toggle-on' }),
            priority: property({ type: INT, icon: 'far-lightbulb' }),
            state: property({ type: STRING, icon: 'far-heart' }),

            ownerUser: property({ type: JSON_VARIANT, icon: 'far-user' }),
            ownerGroup: property({ type: JSON_VARIANT, icon: 'far-users' }),

            acl: property({ type: INT, icon: 'far-user-lock' }),
            accessMode: property({ type: INT, icon: 'far-key' }),
        });
    }

    static readonly INSTANCE = new CoObjectTypeInfo(UNDEFINED);

}

export default CoObjectTypeInfo;