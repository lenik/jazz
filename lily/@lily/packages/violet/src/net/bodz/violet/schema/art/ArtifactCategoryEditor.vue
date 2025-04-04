<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { CoNode_TYPE } from "lily-basic/src/net/bodz/lily/concrete/CoNodeTypeInfo";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import ArtifactCategory from "./ArtifactCategory";
import { _ArtifactCategory_stuff_TYPE } from "./_ArtifactCategory_stuff_TypeInfo";

export const title = "Editor view of: Artifact category";
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

import ArtifactCategoryChooseDialog from "./ArtifactCategoryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArtifactCategory>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArtifactCategory.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactCategoryChooseDialog = ref<InstanceType<typeof ArtifactCategoryChooseDialog>>();
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
        <FieldGroup :type="CoNode_TYPE">
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="artifactCategoryChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArtifactCategory_stuff_TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
            <FieldRow :property="meta.skufmt" v-model="model.skufmt">
                <input type="text" v-model="model.skufmt" />
            </FieldRow>
            <FieldRow :property="meta.skufmtfull" v-model="model.skufmtfull">
                <input type="text" v-model="model.skufmtfull" />
            </FieldRow>
            <FieldRow :property="meta.barfmt" v-model="model.barfmt">
                <input type="text" v-model="model.barfmt" />
            </FieldRow>
            <FieldRow :property="meta.barfmtfull" v-model="model.barfmtfull">
                <input type="text" v-model="model.barfmtfull" />
            </FieldRow>
            <FieldRow :property="meta.batchfmt" v-model="model.batchfmt">
                <input type="text" v-model="model.batchfmt" />
            </FieldRow>
            <FieldRow :property="meta.serialfmt" v-model="model.serialfmt">
                <input type="text" v-model="model.serialfmt" />
            </FieldRow>
            <FieldRow :property="meta.count" v-model="model.count">
                <input type="number" v-model="model.count" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactCategoryChooseDialog ref="artifactCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
