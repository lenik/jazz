<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { OrgUnit_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrgUnitTypeInfo";

import { FabProcess_TYPE } from "./FabProcessTypeInfo";
import FabTrack from "./FabTrack";

export const title = "Admin view of: Fab track";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import FabTrackEditor from "./FabTrackEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabTrack.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabProcess": FabProcess_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
    "OrgUnit": OrgUnit_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
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
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="FabProcess" data-format="label" data-field="process">Process</th>
            <th data-type="OffsetDateTime" data-field="since">Since</th>
            <th data-type="OffsetDateTime" data-field="deadline">Deadline</th>
            <th data-type="BIG_DECIMAL" data-field="plannedQuantity">Planned Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="actualQuantity">Actual Quantity</th>
            <th data-type="BIG_DECIMAL" data-field="validQuantity">Valid Quantity</th>
            <th data-type="OrgUnit" data-format="label" data-field="orgUnit">Org Unit</th>
        </template>
        <template #preview>
            <FabTrackEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabTrackEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
