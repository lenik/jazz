<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import Badge from "./Badge";
import { _Badge_stuff_TYPE } from "./_Badge_stuff_TypeInfo";

export const title = "Editor view of: Badge";
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

const model = defineModel<Badge>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Badge.TYPE.property;
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
        <FieldGroup :type="_Badge_stuff_TYPE">
            <FieldRow :property="meta.expr" v-model="model.expr">
                <input type="text" v-model="model.expr" />
            </FieldRow>
            <FieldRow :property="meta.val" v-model="model.val">
                <input type="number" v-model="model.val" />
            </FieldRow>
            <FieldRow :property="meta.levels" v-model="model.levels">
            </FieldRow>
            <FieldRow :property="meta.descend" v-model="model.descend">
                <input type="checkbox" v-model="model.descend" />
            </FieldRow>
            <FieldRow :property="meta.transient_" v-model="model.transient_">
                <input type="checkbox" v-model="model.transient_" />
            </FieldRow>
            <FieldRow :property="meta.indexed" v-model="model.indexed">
                <input type="checkbox" v-model="model.indexed" />
            </FieldRow>
        </FieldGroup>
    </div>
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
