<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";

import Artifact from "../art/Artifact";
import Region from "../store/Region";
import OrgAsset from "./OrgAsset";

export const title = "Admin view of: Org asset";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import OrgAssetEditor from "./OrgAssetEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = OrgAsset.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "Organization": Organization.TYPE,
    "Artifact": Artifact.TYPE,
    "Region": Region.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "BIG_DECIMAL": BIG_DECIMAL,
    "ZonedDateTime": ZonedDateTime.TYPE,
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
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="Organization" data-format="label" data-field="owner">Owner</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="Region" data-format="label" data-field="region">Region</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="LONG" data-field="serial">Serial</th>
            <th data-type="ZonedDateTime" data-field="expire">Expire</th>
        </template>
        <template #preview>
            <OrgAssetEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <OrgAssetEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
