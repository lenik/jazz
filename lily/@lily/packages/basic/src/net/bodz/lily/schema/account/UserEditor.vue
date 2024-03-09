<script lang="ts">
import { onMounted, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { Timestamp, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import CoObject from "../../concrete/CoObject";
import CoPrincipal from "../../concrete/CoPrincipal";
import IdEntity from "../../concrete/IdEntity";
import StructRow from "../../concrete/StructRow";
import User from "./User";
import _User_stuff from "./_User_stuff";

export const title = "Editor view of: User";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

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
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="CoPrincipal.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_User_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.type" v-model="model.type">
                <RefEditor :dialog="userTypeChooseDialog" v-model="model.type" v-model:id="model.typeId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.primaryGroup" v-model="model.primaryGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.primaryGroup" v-model:id="model.primaryGroupId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.referer" v-model="model.referer">
                <RefEditor :dialog="userChooseDialog" v-model="model.referer" v-model:id="model.refererId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.person" v-model="model.person">
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
