<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { CoNode_TYPE } from "lily-basic/src/net/bodz/lily/concrete/CoNodeTypeInfo";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabStdTestCategory from "./FabStdTestCategory";
import { _FabStdTestCategory_stuff_TYPE } from "./_FabStdTestCategory_stuff_TypeInfo";

export const title = "Editor view of: Fab std test category";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "lily-basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import FabStdTestCategoryChooseDialog from "./FabStdTestCategoryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdTestCategory>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdTestCategory.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabStdTestCategoryChooseDialog = ref<InstanceType<typeof FabStdTestCategoryChooseDialog>>();
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
            <FieldRow :property="meta.refCount" v-model="model.refCount">
                <input type="number" v-model="model.refCount" disabled />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="fabStdTestCategoryChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_FabStdTestCategory_stuff_TYPE">
            <FieldRow :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabStdTestCategoryChooseDialog ref="fabStdTestCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
