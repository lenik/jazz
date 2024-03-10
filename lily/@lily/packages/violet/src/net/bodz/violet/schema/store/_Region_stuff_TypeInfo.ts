import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoNodeTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoNodeTypeInfo";

import Artifact from "../art/Artifact";
import ArtifactCategory from "../art/ArtifactCategory";
import Region from "./Region";
import RegionCategory from "./RegionCategory";
import RegionLevel from "./RegionLevel";
import RegionTag from "./RegionTag";
import _Region_stuff_Validators from "./_Region_stuff_Validators";

export class _Region_stuff_TypeInfo extends CoNodeTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "region";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_PATH = "path";
    static readonly FIELD_PROTO_ID = "proto";
    static readonly FIELD_HEIGHT = "height";
    static readonly FIELD_LEVEL_ID = "level";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_TAG_ID = "tag";
    static readonly FIELD_MATERIAL_ID = "material";
    static readonly FIELD_FOR_CAT_ID = "for_cat";
    static readonly FIELD_FOR_ART_ID = "for_art";
    static readonly FIELD_ARTIFACT_ID = "art";

    static readonly N_CODE = 10;
    static readonly N_PATH = 200;
    static readonly N_PROTO_ID = 10;
    static readonly N_HEIGHT = 10;
    static readonly N_LEVEL_ID = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_TAG_ID = 10;
    static readonly N_MATERIAL_ID = 10;
    static readonly N_FOR_CAT_ID = 10;
    static readonly N_FOR_ART_ID = 10;
    static readonly N_ARTIFACT_ID = 10;

    readonly validators = new _Region_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.store.Region"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 10, validator: this.validators.validateCode }),
            path: property({ type: STRING, nullable: false, precision: 200, validator: this.validators.validatePath }),
            height: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHeight }),

            forCat: property({ type: ArtifactCategory.TYPE, validator: this.validators.validateForCat }),
            forCatId: property({ type: INT, precision: 10 }),

            proto: property({ type: this, validator: this.validators.validateProto }),
            protoId: property({ type: INT, precision: 10 }),

            tag: property({ type: RegionTag.TYPE, validator: this.validators.validateTag }),
            tagId: property({ type: INT, precision: 10 }),

            category: property({ type: RegionCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            level: property({ type: RegionLevel.TYPE, nullable: false, validator: this.validators.validateLevel }),
            levelId: property({ type: INT, nullable: false, precision: 10 }),

            artifact: property({ type: Artifact.TYPE, validator: this.validators.validateArtifact }),
            artifactId: property({ type: INT, precision: 10 }),

            material: property({ type: Artifact.TYPE, validator: this.validators.validateMaterial }),
            materialId: property({ type: INT, precision: 10 }),

            forArt: property({ type: Artifact.TYPE, validator: this.validators.validateForArt }),
            forArtId: property({ type: INT, precision: 10 }),
        });
    }

}

export default _Region_stuff_TypeInfo;
