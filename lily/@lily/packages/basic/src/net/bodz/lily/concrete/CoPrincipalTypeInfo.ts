import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import CoPrincipalValidators from './CoPrincipalValidators';

export class CoPrincipalTypeInfo extends CoObjectTypeInfo {

    name = "net.bodz.lily.concrete.CoPrincipal"
    icon = "fa-user-lock"
    label = "Concrete Principal"
    description = "Access control entity."

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