<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { BigDecimal, int, short } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import Artifact from "./Artifact";
import { _Artifact_stuff_TYPE } from "./_Artifact_stuff_TypeInfo";

export const title = "Editor view of: Artifact";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";
import UomRowChooseDialog from "lily-basic/src/net/bodz/lily/schema/util/UomRowChooseDialog.vue";

import ArtifactCategoryChooseDialog from "./ArtifactCategoryChooseDialog.vue";
import ArtifactChooseDialog from "./ArtifactChooseDialog.vue";
import ArtifactPhaseChooseDialog from "./ArtifactPhaseChooseDialog.vue";
import ArtifactTypeChooseDialog from "./ArtifactTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Artifact>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Artifact.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactChooseDialog = ref<InstanceType<typeof ArtifactChooseDialog>>();
const artifactTypeChooseDialog = ref<InstanceType<typeof ArtifactTypeChooseDialog>>();
const artifactCategoryChooseDialog = ref<InstanceType<typeof ArtifactCategoryChooseDialog>>();
const artifactPhaseChooseDialog = ref<InstanceType<typeof ArtifactPhaseChooseDialog>>();
const uomRowChooseDialog = ref<InstanceType<typeof UomRowChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Artifact_stuff_TYPE">
            <FieldRow :property="meta.skuCode" v-model="model.skuCode">
                <input type="text" v-model="model.skuCode" />
            </FieldRow>
            <FieldRow :property="meta.barCode" v-model="model.barCode">
                <input type="text" v-model="model.barCode" />
            </FieldRow>
            <FieldRow :property="meta.rfidCode" v-model="model.rfidCode">
                <input type="text" v-model="model.rfidCode" />
            </FieldRow>
            <FieldRow :property="meta.modelName" v-model="model.modelName">
                <input type="text" v-model="model.modelName" />
            </FieldRow>
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
            <FieldRow :property="meta.finish" v-model="model.finish">
                <input type="number" v-model="model.finish" />
            </FieldRow>
            <FieldRow :property="meta.price" v-model="model.price">
                <input type="number" v-model="model.price" />
            </FieldRow>
            <FieldRow :property="meta.proto" v-model="model.proto">
                <RefEditor :dialog="artifactChooseDialog" v-model="model.proto" v-model:id="model.protoId" />
            </FieldRow>
            <FieldRow :property="meta.type" v-model="model.type">
                <RefEditor :dialog="artifactTypeChooseDialog" v-model="model.type" v-model:id="model.typeId" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="artifactCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.phase" v-model="model.phase">
                <RefEditor :dialog="artifactPhaseChooseDialog" v-model="model.phase" v-model:id="model.phaseId" />
            </FieldRow>
            <FieldRow :property="meta.uom" v-model="model.uom">
                <RefEditor :dialog="uomRowChooseDialog" v-model="model.uom" v-model:id="model.uomId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactChooseDialog ref="artifactChooseDialog" />
    <ArtifactTypeChooseDialog ref="artifactTypeChooseDialog" />
    <ArtifactCategoryChooseDialog ref="artifactCategoryChooseDialog" />
    <ArtifactPhaseChooseDialog ref="artifactPhaseChooseDialog" />
    <UomRowChooseDialog ref="uomRowChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
