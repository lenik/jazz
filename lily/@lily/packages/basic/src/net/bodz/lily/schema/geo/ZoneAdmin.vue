<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";

import Zone from "./Zone";

export const title = "Admin view of: Zone";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import ZoneEditor from "./ZoneEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Zone.TYPE;
const selection = ref<any>({});
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-field="code">Code</th>
            <th data-type="string" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="string" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="integer" data-field="accessMode">Access Mode</th>
            <th data-type="integer" data-field="acl">Acl</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="Moment" data-field="creationDate">Creation Date</th>
            <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="string" data-format="label" data-field="category">Category</th>
            <th data-type="string" data-field="country">Country</th>
            <th data-type="string" data-format="label" data-field="parent">Parent</th>
            <th data-type="integer" data-field="depth">Depth</th>
            <th data-type="string" data-field="telCode">Tel Code</th>
            <th data-type="string" data-field="postCode">Post Code</th>
            <th data-type="any" data-field="properties">Properties</th>
            <th data-type="any" data-field="data">Data</th>
        </template>
        <template #preview>
            <ZoneEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <ZoneEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
