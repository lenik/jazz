<script lang="ts">
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import StructRow from "../../concrete/StructRow";
import PersonTag from "./PersonTag";
import _PersonTag_stuff from "./_PersonTag_stuff";

export const title = "Editor view of: Person tag";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import PersonChooseDialog from "./PersonChooseDialog.vue";
import PersonTagTypeChooseDialog from "./PersonTagTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<PersonTag>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = PersonTag.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const personTagTypeChooseDialog = ref<InstanceType<typeof PersonTagTypeChooseDialog>>();
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
        <FieldGroup :type="_PersonTag_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.tag" v-model="model.tag">
                <RefEditor :dialog="personTagTypeChooseDialog" v-model="model.tag" v-model:id="model.tagId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PersonChooseDialog ref="personChooseDialog" />
    <PersonTagTypeChooseDialog ref="personTagTypeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
