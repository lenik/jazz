<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import type { Uom } from "./Uom";
import UomChooseDialog from "./UomChooseDialog.vue";

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

const meta = Uom.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const uomChooseDialog = ref<InstanceType<typeof UomChooseDialog>>();
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
            <FieldRow v-bind="fieldRowProps" :property="meta.properties" v-model="model.properties">
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.util._Uom_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.prop" v-model="model.prop">
                <input type="text" v-model="model.prop" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.scale" v-model="model.scale">
                <input type="number" v-model="model.scale" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.std" v-model="model.std">
                <RefEditor :dialog="uomChooseDialog" v-model="model.stdId" v-model:id="model.stdId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.util.Uom">
        </FieldGroup>
    </div>
    <UomChooseDialog ref="uomChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
