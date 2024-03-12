import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoEntityValidators from './CoEntityValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import { INT, LONG } from '@skeljs/core/src/lang/baseinfo';

export class CoEntityTypeInfo extends CoObjectTypeInfo {

    readonly validators = new CoEntityValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.concrete.CoEntity"; }
    get icon() { return "far-cube"; }
    get label() { return "Concrete Entity"; }
    get description() { return "An entity always has an identity."; }

    override preamble() {
        super.preamble();
        this.declare({
            // id: primaryKey({ type: LONG, precision: 20, })
        });
    }

    static readonly INSTANCE = new CoEntityTypeInfo();

}

export default CoEntityTypeInfo;