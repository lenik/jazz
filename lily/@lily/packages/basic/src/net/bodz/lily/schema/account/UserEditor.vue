<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";

import { IdEntity_TYPE } from "../../concrete/IdEntityTypeInfo";
import User from "./User";
import { _User_stuff_TYPE } from "./_User_stuff_TypeInfo";

export const title = "Editor view of: User";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

import CoImagedFieldGroup from "../../concrete/CoImagedFieldGroup.vue";
import CoObjectFieldGroup from "../../concrete/CoObjectFieldGroup.vue";
import CoPrincipalFieldGroup from "../../concrete/CoPrincipalFieldGroup.vue";
import StructRowFieldGroup from "../../concrete/StructRowFieldGroup.vue";
import PersonChooseDialog from "../contact/PersonChooseDialog.vue";
import GroupChooseDialog from "./GroupChooseDialog.vue";
import UserChooseDialog from "./UserChooseDialog.vue";
import UserTypeChooseDialog from "./UserTypeChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<User>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = User.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const userTypeChooseDialog = ref<InstanceType<typeof UserTypeChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
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
        <CoPrincipalFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="_User_stuff_TYPE">
            <FieldRow :property="meta.files" v-model="model.files">
                <JsonEditor v-model="model.files" />
            </FieldRow>
            <FieldRow :property="meta.type" v-model="model.type">
                <RefEditor :dialog="userTypeChooseDialog" v-model="model.type" v-model:id="model.typeId" />
            </FieldRow>
            <FieldRow :property="meta.primaryGroup" v-model="model.primaryGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.primaryGroup" v-model:id="model.primaryGroupId" />
            </FieldRow>
            <FieldRow :property="meta.referer" v-model="model.referer">
                <RefEditor :dialog="userChooseDialog" v-model="model.referer" v-model:id="model.refererId" />
            </FieldRow>
            <FieldRow :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserTypeChooseDialog ref="userTypeChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <UserChooseDialog ref="userChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
