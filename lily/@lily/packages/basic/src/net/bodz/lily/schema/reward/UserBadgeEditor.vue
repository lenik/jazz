<script lang="ts">

import { onMounted } from "vue";

import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";

import UserChooseDialog from "../account/UserChooseDialog.vue";
import BadgeChooseDialog from "./BadgeChooseDialog.vue";
import type { UserBadge } from "./UserBadge";

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

const meta = UserBadge.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const badgeChooseDialog = ref<InstanceType<typeof BadgeChooseDialog>>();
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
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.concrete.CoEntity">
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.reward._UserBadge_stuff">
            <FieldRow v-bind="fieldRowProps" :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.userId" v-model:id="model.userId" />
            </FieldRow>
            <FieldRow v-bind="fieldRowProps" :property="meta.badge" v-model="model.badge">
                <RefEditor :dialog="badgeChooseDialog" v-model="model.badgeId" v-model:id="model.badgeId" />
            </FieldRow>
        </FieldGroup>
        <FieldGroup decl="net.bodz.lily.schema.reward.UserBadge">
        </FieldGroup>
    </div>
    <UserChooseDialog ref="userChooseDialog" />
    <BadgeChooseDialog ref="badgeChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
