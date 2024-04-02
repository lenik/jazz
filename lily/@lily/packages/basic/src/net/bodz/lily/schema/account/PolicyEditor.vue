<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import Policy from "./Policy";
import { _Policy_stuff_TYPE } from "./_Policy_stuff_TypeInfo";

export const title = "Editor view of: Policy";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Policy>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Policy.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
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
        <FieldGroup :type="_Policy_stuff_TYPE">
            <FieldRow :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
            <FieldRow :property="meta.controlClass" v-model="model.controlClass">
                <input type="text" v-model="model.controlClass" />
            </FieldRow>
            <FieldRow :property="meta.methodName" v-model="model.methodName">
                <input type="text" v-model="model.methodName" />
            </FieldRow>
            <FieldRow :property="meta.allowBits" v-model="model.allowBits">
                <input type="number" v-model="model.allowBits" />
            </FieldRow>
            <FieldRow :property="meta.denyBits" v-model="model.denyBits">
                <input type="number" v-model="model.denyBits" />
            </FieldRow>
        </FieldGroup>
    </div>
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
