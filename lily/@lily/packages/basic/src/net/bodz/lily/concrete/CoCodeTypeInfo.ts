import { primaryKey, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoNodeTypeInfo from './CoNodeTypeInfo';
import CoCodeValidators from './CoCodeValidators';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { STRING } from '@skeljs/core/src/lang/baseinfo';

export class CoCodeTypeInfo extends CoNodeTypeInfo {

    readonly validators = new CoCodeValidators(this);

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(selfType, idType);
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

}

export default CoCodeTypeInfo;