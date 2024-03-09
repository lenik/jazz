<script lang="ts">
import { onMounted, ref } from "vue";

import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { Timestamp, int } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import CoObject from "../../concrete/CoObject";
import StructRow from "../../concrete/StructRow";
import ExternalSite from "./ExternalSite";
import _ExternalSite_stuff from "./_ExternalSite_stuff";

export const title = "Editor view of: External site";
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
import ExternalSiteChooseDialog from "./ExternalSiteChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ExternalSite>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ExternalSite.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();
const externalSiteChooseDialog = ref<InstanceType<typeof ExternalSiteChooseDialog>>();
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
        <FieldGroup :type="_ExternalSite_stuff.TYPE">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.depth" v-model="model.depth">
                <input type="number" v-model="model.depth" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.urlfmt" v-model="model.urlfmt">
                <input type="text" v-model="model.urlfmt" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.bonus" v-model="model.bonus">
                <input type="number" v-model="model.bonus" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.count" v-model="model.count">
                <input type="number" v-model="model.count" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.parent" v-model="model.parent">
                <RefEditor :dialog="externalSiteChooseDialog" v-model="model.parent" v-model:id="model.parentId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
    <ExternalSiteChooseDialog ref="externalSiteChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
