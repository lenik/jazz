<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import FormDefChooseDialog from "./FormDefChooseDialog.vue";
import type { FormParameter } from "./FormParameter";

export interface Props {
}
</script>

<script setup lang="ts">
defineOptions({
    inheritAttrs: false
});

const model = defineModel<%s>();Person

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = FormParameter.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const formDefChooseDialog = ref<InstanceType<typeof FormDefChooseDialog>>();
const valids = ref<any>({});

// methods

defineExpose({ update });

function update() {
}

onMounted(() => {
});

</script>

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.meta._FormParameter_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.value" v-model="model.value">
                <input type="text" v-model="model.value" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.form" v-model="model.form">
                <RefEditor :dialog="formDefChooseDialog" v-model="model.formId" v-model:id="model.formId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.meta.FormParameter">
        </FieldGroup>
    </div>
    <FormDefChooseDialog ref="formDefChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
