import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoEntityValidators from './CoEntityValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import { INT, LONG } from '@skeljs/core/src/lang/baseinfo';

export class CoEntityTypeInfo extends CoObjectTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoEntity"; }
    get icon() { return "far-cube"; }
    get label() { return "Concrete Entity"; }
    get description() { return "An entity always has an identity."; }

    validators = new CoEntityValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: LONG, precision: 20, })
        });
    }

    constructor() {
        super();
    }

}

export default CoEntityTypeInfo;