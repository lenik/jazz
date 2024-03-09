<script lang="ts">
import { onMounted, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { Timestamp, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import CoObject from "../../concrete/CoObject";
import StructRow from "../../concrete/StructRow";
import Policy from "./Policy";
import _Policy_stuff from "./_Policy_stuff";

export const title = "Editor view of: Policy";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

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
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.creationDate" v-model="model.creationDate">
                <input type="datetime" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="datetime" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
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
            <FieldRow v-bind="fieldRowProps" :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.properties" v-model="model.properties">
                <JsonEditor v-model="model.properties" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_Policy_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.controlClass" v-model="model.controlClass">
                <input type="text" v-model="model.controlClass" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.methodName" v-model="model.methodName">
                <input type="text" v-model="model.methodName" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.allowBits" v-model="model.allowBits">
                <input type="number" v-model="model.allowBits" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.denyBits" v-model="model.denyBits">
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
