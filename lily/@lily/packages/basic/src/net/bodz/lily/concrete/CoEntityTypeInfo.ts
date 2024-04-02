import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoEntityValidators from './CoEntityValidators';
import CoObjectTypeInfo from './CoObjectTypeInfo';
import { INT, LONG, UNDEFINED } from '@skeljs/core/src/lang/baseinfo';

export class CoEntityTypeInfo extends CoObjectTypeInfo {

    readonly validators = new CoEntityValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
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

    static readonly INSTANCE = new CoEntityTypeInfo(UNDEFINED);

}

export default CoEntityTypeInfo;
export const CoEntity_TYPE = CoEntityTypeInfo.INSTANCE;
