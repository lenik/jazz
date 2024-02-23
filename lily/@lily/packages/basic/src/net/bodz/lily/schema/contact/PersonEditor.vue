<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import GroupChooseDialog from "../account/GroupChooseDialog.vue";
import UserChooseDialog from "../account/UserChooseDialog.vue";
import PartyCategoryChooseDialog from "./PartyCategoryChooseDialog.vue";
import type { Person } from "./Person";
import PersonChooseDialog from "./PersonChooseDialog.vue";

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

const meta = Person.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const partyCategoryChooseDialog = ref<InstanceType<typeof PartyCategoryChooseDialog>>();
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
            <FieldRow v-bind="fieldRowProps" :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
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
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUserId" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroupId" v-model:id="model.ownerGroupId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.IdEntity">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.contact.Party">
            <FieldRow v-bind="fieldRowProps" :property="meta.birthday" v-model="model.birthday">
                <input type="date" v-model="model.birthday" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.langTag" v-model="model.langTag">
                <input type="text" v-model="model.langTag" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.timeZone" v-model="model.timeZone">
                <input type="text" v-model="model.timeZone" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.customer" v-model="model.customer">
                <input type="checkbox" v-model="model.customer" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.supplier" v-model="model.supplier">
                <input type="checkbox" v-model="model.supplier" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.category" v-model="model.category">
                <RefEditor :dialog="partyCategoryChooseDialog" v-model="model.categoryId" v-model:id="model.categoryId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.contact._Person_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.label" v-model="model.label">
                <input type="text" v-model="model.label" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.description" v-model="model.description">
                <input type="text" v-model="model.description" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.roleCount" v-model="model.roleCount">
                <input type="number" v-model="model.roleCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.employee" v-model="model.employee">
                <input type="checkbox" v-model="model.employee" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.bankCount" v-model="model.bankCount">
                <input type="number" v-model="model.bankCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.gender" v-model="model.gender">
                <input type="text" v-model="model.gender" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.homeland" v-model="model.homeland">
                <input type="text" v-model="model.homeland" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.passport" v-model="model.passport">
                <input type="text" v-model="model.passport" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ssn" v-model="model.ssn">
                <input type="text" v-model="model.ssn" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.dln" v-model="model.dln">
                <input type="text" v-model="model.dln" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.father" v-model="model.father">
                <RefEditor :dialog="personChooseDialog" v-model="model.fatherId" v-model:id="model.fatherId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.mother" v-model="model.mother">
                <RefEditor :dialog="personChooseDialog" v-model="model.motherId" v-model:id="model.motherId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.contact.Person">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <PartyCategoryChooseDialog ref="partyCategoryChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>