<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { ArtifactModel_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import { FabFn_TYPE } from "./FabFnTypeInfo";
import FabStdProcess from "./FabStdProcess";
import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";

export const title = "Admin view of: Fab std process";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import FabStdProcessEditor from "./FabStdProcessEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabStdProcess.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "BOOLEAN": BOOLEAN,
    "JSON_VARIANT": JSON_VARIANT,
    "ArtifactModel": ArtifactModel_TYPE,
    "FabFn": FabFn_TYPE,
    "FabStdTest": FabStdTest_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="BOOLEAN" data-field="valid">Valid</th>
            <th data-type="OffsetDateTime" data-field="validSince">Valid Since</th>
            <th data-type="OffsetDateTime" data-field="validUntil">Valid Until</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="ArtifactModel" data-format="label" data-field="output">Output</th>
            <th data-type="FabFn" data-format="label" data-field="function">Function</th>
            <th data-type="INT" data-field="duration">Duration</th>
            <th data-type="BOOLEAN" data-field="strict">Strict</th>
            <th data-type="FabStdTest" data-format="label" data-field="test">Test</th>
        </template>
        <template #preview>
            <FabStdProcessEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabStdProcessEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
