<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { CoNode_TYPE } from "../../concrete/CoNodeTypeInfo";
import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import CategoryDef from "./CategoryDef";
import { _CategoryDef_stuff_TYPE } from "./_CategoryDef_stuff_TypeInfo";

export const title = "Editor view of: Category def";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import CategoryDefChooseDialog from "./CategoryDefChooseDialog.vue";
import SchemaDefChooseDialog from "./SchemaDefChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<CategoryDef>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = CategoryDef.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const categoryDefChooseDialog = ref<InstanceType<typeof CategoryDefChooseDialog>>();
const schemaDefChooseDialog = ref<InstanceType<typeof SchemaDefChooseDialog>>();
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
                <RefEditor :dialog="categoryDefChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_CategoryDef_stuff_TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.schema" v-model="model.schema">
                <RefEditor :dialog="schemaDefChooseDialog" v-model="model.schema" v-model:id="model.schemaId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <CategoryDefChooseDialog ref="categoryDefChooseDialog" />
    <SchemaDefChooseDialog ref="schemaDefChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
