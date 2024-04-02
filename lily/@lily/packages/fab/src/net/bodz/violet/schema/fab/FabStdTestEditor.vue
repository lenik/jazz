<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabStdTest from "./FabStdTest";
import { _FabStdTest_stuff_TYPE } from "./_FabStdTest_stuff_TypeInfo";

export const title = "Editor view of: Fab std test";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoImagedFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import FabStdTestCategoryChooseDialog from "./FabStdTestCategoryChooseDialog.vue";
import FabStdTestChooseDialog from "./FabStdTestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<FabStdTest>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FabStdTest.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const fabStdTestCategoryChooseDialog = ref<InstanceType<typeof FabStdTestCategoryChooseDialog>>();
const fabStdTestChooseDialog = ref<InstanceType<typeof FabStdTestChooseDialog>>();
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
        <FieldGroup :type="_FabStdTest_stuff_TYPE">
            <FieldRow :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow :property="meta.depth" v-model="model.depth">
                <input type="number" v-model="model.depth" />
            </FieldRow>
            <FieldRow :property="meta.refCount" v-model="model.refCount">
                <input type="number" v-model="model.refCount" />
            </FieldRow>
            <FieldRow :property="meta.category" v-model="model.category">
                <RefEditor :dialog="fabStdTestCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="fabStdTestChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <FabStdTestCategoryChooseDialog ref="fabStdTestCategoryChooseDialog" />
    <FabStdTestChooseDialog ref="fabStdTestChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
