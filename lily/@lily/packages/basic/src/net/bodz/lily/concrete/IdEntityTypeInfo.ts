import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import IdEntityValidators from './IdEntityValidators';

export class IdEntityTypeInfo extends CoObjectTypeInfo {

    get name() { return "net.bodz.lily.concrete.IdEntity"; }
    get icon() { return "fa-key"; }
    get label() { return "Concrete IdEntity"; }
    get description() { return "An entity has an id column."; }

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