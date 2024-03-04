import { EntityPropertyMap, primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoEntityValidators from './CoEntityValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';

export class CoEntityTypeInfo extends CoObjectTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoEntity"; }
    get icon() { return "far-cube"; }
    get label() { return "Concrete Entity"; }
    get description() { return "An entity always has an identity."; }

    validators = new CoEntityValidators(this);

    declaredProperty: EntityPropertyMap = {
        // id: primaryKey({ type: 'number', precision: 20, })
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoEntityTypeInfo;