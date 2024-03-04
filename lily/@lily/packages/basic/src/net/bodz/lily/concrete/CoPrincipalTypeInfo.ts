import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import CoPrincipalValidators from './CoPrincipalValidators';

export class CoPrincipalTypeInfo extends CoObjectTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoPrincipal"; }
    get icon() { return "fa-user-lock"; }
    get label() { return "Concrete Principal"; }
    get description() { return "Access control entity."; }

    validators = new CoPrincipalValidators(this);

    declaredProperty: EntityPropertyMap = {
        name: property({ type: 'string', precision: 30, }),
        properties: property({ type: 'any' }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoPrincipalTypeInfo;