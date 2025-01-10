<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { long } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import VAppRequestApi from "./VAppRequestApi";
import { _VAppRequestApi_stuff_TYPE } from "./_VAppRequestApi_stuff_TypeInfo";

export const title = "Editor view of: V app request api";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import ApiTypeChooseDialog from "./ApiTypeChooseDialog.vue";
import VAppRequestChooseDialog from "./VAppRequestChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<VAppRequestApi>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = VAppRequestApi.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const vAppRequestChooseDialog = ref<InstanceType<typeof VAppRequestChooseDialog>>();
const apiTypeChooseDialog = ref<InstanceType<typeof ApiTypeChooseDialog>>();
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
        <FieldGroup :type="_VAppRequestApi_stuff_TYPE">
            <FieldRow :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="vAppRequestChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
            <FieldRow :property="meta.api" v-model="model.api">
                <RefEditor :dialog="apiTypeChooseDialog" v-model="model.api" v-model:id="model.apiId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <VAppRequestChooseDialog ref="vAppRequestChooseDialog" />
    <ApiTypeChooseDialog ref="apiTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
