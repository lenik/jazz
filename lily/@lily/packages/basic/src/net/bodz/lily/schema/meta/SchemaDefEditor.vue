<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import SchemaDef from "./SchemaDef";
import { _SchemaDef_stuff_TYPE } from "./_SchemaDef_stuff_TypeInfo";

export const title = "Editor view of: Schema def";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoCodeFieldGroup from "../../concrete/CoCodeFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<SchemaDef>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = SchemaDef.TYPE.property;
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
        <CoCodeFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_SchemaDef_stuff_TYPE">
            <FieldRow :property="meta.dummy" v-model="model.dummy">
                <input type="number" v-model="model.dummy" />
            </FieldRow>
        </FieldGroup>
    </div>
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
