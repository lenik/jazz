<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type { Timestamp } from "skel01-core/src/lang/time";
import { CoTalk_TYPE } from "./CoTalkTypeInfo";
import CoTalk from "./CoTalk";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import GroupChooseDialog from "../schema/account/GroupChooseDialog.vue";
import UserChooseDialog from "../schema/account/UserChooseDialog.vue";

const model = defineModel<CoTalk>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const groupChooseDialog = ref<InstanceType<typeof GroupChooseDialog>>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoTalk_TYPE" ref="rootElement">
    </FieldGroup>
    <UserChooseDialog ref="userChooseDialog" />
    <GroupChooseDialog ref="groupChooseDialog" />
</template>

<style scoped lang="scss"></style>
