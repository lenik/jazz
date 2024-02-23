<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import CoObject from "@skeljs/dba/src/net/bodz/lily/concrete/CoObject";
import StructRow from "@skeljs/dba/src/net/bodz/lily/concrete/StructRow";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import VApiCredit from "./VApiCredit";
import _VApiCredit_stuff from "./_VApiCredit_stuff";

export const title = "Editor view of: V api credit";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import ApiTypeChooseDialog from "./ApiTypeChooseDialog.vue";
import VAppChooseDialog from "./VAppChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<VApiCredit>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = VApiCredit.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const vAppChooseDialog = ref<InstanceType<typeof VAppChooseDialog>>();
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
        <FieldGroup :type="StructRow.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.creationDate" v-model="model.creationDate">
                <input type="date" v-model="model.creationDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.lastModifiedDate" v-model="model.lastModifiedDate">
                <input type="date" v-model="model.lastModifiedDate" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.version" v-model="model.version">
                <input type="number" v-model="model.version" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoObject.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.priority" v-model="model.priority">
                <input type="number" v-model="model.priority" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.flags" v-model="model.flags">
                <input type="number" v-model="model.flags" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.state" v-model="model.state">
                <input type="number" v-model="model.state" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_VApiCredit_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.credit" v-model="model.credit">
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.app" v-model="model.app">
                <RefEditor :dialog="vAppChooseDialog" v-model="model.app" v-model:id="model.appId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.api" v-model="model.api">
                <RefEditor :dialog="apiTypeChooseDialog" v-model="model.api" v-model:id="model.apiId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <VAppChooseDialog ref="vAppChooseDialog" />
    <ApiTypeChooseDialog ref="apiTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
