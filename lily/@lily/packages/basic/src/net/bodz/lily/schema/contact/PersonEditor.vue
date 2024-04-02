<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import type Gender from "./Gender";
import Person from "./Person";
import { _Person_stuff_TYPE } from "./_Person_stuff_TypeInfo";

export const title = "Editor view of: Person";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import PartyFieldGroup from "./PartyFieldGroup.vue";
import PersonChooseDialog from "./PersonChooseDialog.vue";

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
provide(FIELD_ROW_PROPS, fieldRowProps);

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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <CoImagedFieldGroup :meta="meta" v-model="model" />
        <PartyFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_Person_stuff_TYPE">
            <FieldRow :property="meta.roleCount" v-model="model.roleCount">
                <input type="number" v-model="model.roleCount" />
            </FieldRow>
            <FieldRow :property="meta.employee" v-model="model.employee">
                <input type="checkbox" v-model="model.employee" />
            </FieldRow>
            <FieldRow :property="meta.bankCount" v-model="model.bankCount">
                <input type="number" v-model="model.bankCount" />
            </FieldRow>
            <FieldRow :property="meta.gender" v-model="model.gender">
                <select v-model="model.gender">
                    <option value="F">HRT female</option>
                    <option value="M">HRT male</option>
                    <option value="f">female</option>
                    <option value="i">intersex</option>
                    <option value="m">male</option>
                    <option value="n">neutral</option>
                    <option value="o">other</option>
                    <option value="q">queer</option>
                    <option value="x">FtM</option>
                    <option value="y">MtF</option>
                </select>
            </FieldRow>
            <FieldRow :property="meta.pronoun" v-model="model.pronoun">
                <input type="text" v-model="model.pronoun" />
            </FieldRow>
            <FieldRow :property="meta.sexualOrientation" v-model="model.sexualOrientation">
                <input type="text" v-model="model.sexualOrientation" />
            </FieldRow>
            <FieldRow :property="meta.homeland" v-model="model.homeland">
                <input type="text" v-model="model.homeland" />
            </FieldRow>
            <FieldRow :property="meta.passport" v-model="model.passport">
                <input type="text" v-model="model.passport" />
            </FieldRow>
            <FieldRow :property="meta.ssn" v-model="model.ssn">
                <input type="text" v-model="model.ssn" />
            </FieldRow>
            <FieldRow :property="meta.dln" v-model="model.dln">
                <input type="text" v-model="model.dln" />
            </FieldRow>
            <FieldRow :property="meta.father" v-model="model.father">
                <RefEditor :dialog="personChooseDialog" v-model="model.father" v-model:id="model.fatherId" />
            </FieldRow>
            <FieldRow :property="meta.mother" v-model="model.mother">
                <RefEditor :dialog="personChooseDialog" v-model="model.mother" v-model:id="model.motherId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
