<script lang="ts">
import { onMounted, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { SQLDate, Timestamp, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import CoObject from "../../concrete/CoObject";
import IdEntity from "../../concrete/IdEntity";
import StructRow from "../../concrete/StructRow";
import Organization from "./Organization";
import Party from "./Party";
import _Organization_stuff from "./_Organization_stuff";

export const title = "Editor view of: Organization";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import JsonEditor from "@skeljs/core/src/ui/input/JsonEditor.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

import GroupChooseDialog from "../account/GroupChooseDialog.vue";
import UserChooseDialog from "../account/UserChooseDialog.vue";
import PartyCategoryChooseDialog from "./PartyCategoryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<Organization>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = Organization.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const partyCategoryChooseDialog = ref<InstanceType<typeof PartyCategoryChooseDialog>>();
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
            <FieldRow v-bind="fieldRowProps" :property="meta.accessMode" v-model="model.accessMode">
                <input type="number" v-model="model.accessMode" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.acl" v-model="model.acl">
                <input type="number" v-model="model.acl" />
            </FieldRow>
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
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerUser" v-model="model.ownerUser">
                <RefEditor :dialog="userChooseDialog" v-model="model.ownerUser" v-model:id="model.ownerUserId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.ownerGroup" v-model="model.ownerGroup">
                <RefEditor :dialog="groupChooseDialog" v-model="model.ownerGroup" v-model:id="model.ownerGroupId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="IdEntity.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="Party.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.birthday" v-model="model.birthday">
                <input type="date" v-model="model.birthday" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.langTag" v-model="model.langTag">
                <input type="text" v-model="model.langTag" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.timeZone" v-model="model.timeZone">
                <input type="text" v-model="model.timeZone" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.supplier" v-model="model.supplier">
                <input type="checkbox" v-model="model.supplier" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.customer" v-model="model.customer">
                <input type="checkbox" v-model="model.customer" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.subject" v-model="model.subject">
                <input type="text" v-model="model.subject" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.category" v-model="model.category">
                <RefEditor :dialog="partyCategoryChooseDialog" v-model="model.category" v-model:id="model.categoryId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_Organization_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.roleCount" v-model="model.roleCount">
                <input type="number" v-model="model.roleCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.bankCount" v-model="model.bankCount">
                <input type="number" v-model="model.bankCount" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.size" v-model="model.size">
                <input type="number" v-model="model.size" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.taxId" v-model="model.taxId">
                <input type="text" v-model="model.taxId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <PartyCategoryChooseDialog ref="partyCategoryChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
