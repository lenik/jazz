import { primaryKey, property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoNodeTypeInfo from './CoNodeTypeInfo';
import CoCodeValidators from './CoCodeValidators';
import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { INT, STRING } from 'skel01-core/src/lang/baseinfo';

export class CoCodeTypeInfo extends CoNodeTypeInfo {

    readonly validators = new CoCodeValidators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.concrete.CoCode"; }
    get icon() { return "far-hashtag"; }
    get label() { return "Concrete Coded Entity"; }
    get description() { return "This entity has a unique but optinoal code defined, so the object can be referred to by code."; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({
                type: STRING, precision: 30,
                validator: this.validators.validateCode
            })
        });
    }

    static readonly INSTANCE = new CoCodeTypeInfo();
    
}

export default CoCodeTypeInfo;
export const CoCode_TYPE = CoCodeTypeInfo.INSTANCE;
