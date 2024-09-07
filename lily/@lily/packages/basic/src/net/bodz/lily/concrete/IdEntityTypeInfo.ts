import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import IdEntityValidators from './IdEntityValidators';

export class IdEntityTypeInfo extends CoObjectTypeInfo {

    name = "net.bodz.lily.concrete.IdEntity"
    icon = "fa-key"
    label = "Concrete IdEntity"
    description = "An entity has an id column."

    validators = new IdEntityValidators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default IdEntityTypeInfo;