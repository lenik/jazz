<script lang="ts">
import { onMounted, ref } from "vue";
import { Person } from "./Person";
export interface Props {
}    
</script>

<script setup lang="ts">
import FieldRow from 'skel01-core/src/ui/FieldRow.vue';
import JsonEditor from 'skel01-core/src/ui/input/JsonEditor.vue';
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import RefEditor from 'skel01-dba/src/ui/input/RefEditor.vue';
import AttachmentsEditor from 'skel01-dba/src/ui/input/AttachmentsEditor.vue';
import PersonChooseDialog from './PersonChooseDialog.vue';

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Person>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Person.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
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
        <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
            <input type="number" v-model="model.id" disabled>
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.images" v-model="model.images">
            <AttachmentsEditor :className="Person.TYPE.name" v-model="model.images" :id="model.id" />
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
            <input type="text" v-model="model.label" placeholder="enter text...">
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
            <input type="text" v-model="model.description" placeholder="enter text...">
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.properties" v-model="model.properties">
            <JsonEditor v-model="model.properties" />
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.gender" v-model="model.gender">
            <select v-model="model.gender">
                <option value="m">Male</option>
                <option value="f">Female</option>
                <option value="x">Other</option>
            </select>
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.birthday" v-model="model.birthday">
            <input type="date" v-model="model.birthday" placeholder="select birthday...">
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.father" v-model="model.father">
            <RefEditor :dialog="personChooseDialog" v-model="model.father" v-model:id="model.fatherId" />

            <template #description>Father is father</template>
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.mother" v-model="model.mother">
            <RefEditor :dialog="personChooseDialog" v-model="model.mother" v-model:id="model.motherId" />

            <template #description>Mother is mother</template>
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.ssn" v-model="model.ssn">
            <input type="text" v-model="model.ssn" placeholder="enter text...">
        </FieldRow>
        <FieldRow v-bind="fieldRowProps" :property="meta.dln" v-model="model.dln">
            <input type="text" v-model="model.dln" placeholder="enter text...">
        </FieldRow>
    </div>
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
