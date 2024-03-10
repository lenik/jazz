<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";

import Artifact from "../art/Artifact";
import ArtifactCategory from "../art/ArtifactCategory";
import Region from "./Region";
import RegionCategory from "./RegionCategory";
import RegionLevel from "./RegionLevel";
import RegionTag from "./RegionTag";

export const title = "Admin view of: Region";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import RegionEditor from "./RegionEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Region.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Region": Region.TYPE,
    "RegionLevel": RegionLevel.TYPE,
    "RegionCategory": RegionCategory.TYPE,
    "RegionTag": RegionTag.TYPE,
    "Artifact": Artifact.TYPE,
    "ArtifactCategory": ArtifactCategory.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="STRING" data-field="code">Code</th>
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
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="STRING" data-field="path">Path</th>
            <th data-type="Region" data-format="label" data-field="proto">Proto</th>
            <th data-type="Region" data-format="label" data-field="parent">Parent</th>
            <th data-type="INT" data-field="depth">Depth</th>
            <th data-type="INT" data-field="height">Height</th>
            <th data-type="RegionLevel" data-format="label" data-field="level">Level</th>
            <th data-type="RegionCategory" data-format="label" data-field="category">Category</th>
            <th data-type="RegionTag" data-format="label" data-field="tag">Tag</th>
            <th data-type="Artifact" data-format="label" data-field="material">Material</th>
            <th data-type="ArtifactCategory" data-format="label" data-field="forCat">For Cat</th>
            <th data-type="Artifact" data-format="label" data-field="forArt">For Art</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        </template>
        <template #preview>
            <RegionEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <RegionEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
