import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import CoPrincipalValidators from './CoPrincipalValidators';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import { STRING } from '@skeljs/core/src/lang/baseinfo';

export class CoPrincipalTypeInfo extends CoObjectTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoPrincipal"; }
    get icon() { return "fa-user-lock"; }
    get label() { return "Concrete Principal"; }
    get description() { return "Access control entity."; }

    validators = new CoPrincipalValidators(this);

    declaredProperty: EntityPropertyMap = {
        name: property({ type: STRING, precision: 30, }),
        properties: property({ type: JSON_VARIANT }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoPrincipalTypeInfo;