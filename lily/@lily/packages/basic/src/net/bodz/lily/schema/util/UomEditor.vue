<script lang="ts">
import { onMounted, ref } from "vue";

import type { double, integer } from "@skeljs/core/src/lang/type";
import CoObject from "@skeljs/dba/src/net/bodz/lily/concrete/CoObject";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import Uom from "./Uom";
import _Uom_stuff from "./_Uom_stuff";

export const title = "Editor view of: Uom";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import UomChooseDialog from "./UomChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Uom>();

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

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup :type="CoObject.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.icon" v-model="model.icon">
                <input type="text" v-model="model.icon" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.properties" v-model="model.properties">
                <textarea class="json-editor" v-model="model.properties" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_Uom_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.code" v-model="model.code">
                <input type="text" v-model="model.code" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.prop" v-model="model.prop">
                <input type="text" v-model="model.prop" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.scale" v-model="model.scale">
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.std" v-model="model.std">
                <RefEditor :dialog="uomChooseDialog" v-model="model.std" v-model:id="model.stdId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UomChooseDialog ref="uomChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
