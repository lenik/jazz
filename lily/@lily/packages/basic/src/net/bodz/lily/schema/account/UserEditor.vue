<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import PersonChooseDialog from "../contact/PersonChooseDialog.vue";
import GroupChooseDialog from "./GroupChooseDialog.vue";
import type { User } from "./User";
import UserChooseDialog from "./UserChooseDialog.vue";
import UserTypeChooseDialog from "./UserTypeChooseDialog.vue";

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

</script>

<template>
    <div class="entity-editor person-editor" ref="rootElement" v-if="model != null" v-bind="$attrs">
        <FieldGroup decl="net.bodz.lily.concrete.StructRow">
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
        <FieldGroup decl="net.bodz.lily.concrete.CoObject">
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
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.IdEntity">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoPrincipal">
            <FieldRow v-bind="fieldRowProps" :property="meta.name" v-model="model.name">
                <input type="text" v-model="model.name" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.account._User_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.type" v-model="model.type">
                <RefEditor :dialog="userTypeChooseDialog" v-model="model.typeId" v-model:id="model.typeId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.primaryGroup" v-model="model.primaryGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.primaryGroupId" v-model:id="model.primaryGroupId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.referer" v-model="model.referer">
                <RefEditor :dialog="userChooseDialog" v-model="model.refererId" v-model:id="model.refererId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.personId" v-model:id="model.personId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.account.User">
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
