<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { int, long } from "@skeljs/core/src/lang/basetype";
import { getDefaultFieldRowProps } from "@skeljs/dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import DiaryParty from "./DiaryParty";
import { _DiaryParty_stuff_TYPE } from "./_DiaryParty_stuff_TypeInfo";

export const title = "Editor view of: Diary party";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "@skeljs/core/src/ui/FieldRow.vue";
import RefEditor from "@skeljs/dba/src/ui/input/RefEditor.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";
import CoObjectFieldGroup from "@lily/basic/src/net/bodz/lily/concrete/CoObjectFieldGroup.vue";
import UserChooseDialog from "@lily/basic/src/net/bodz/lily/schema/account/UserChooseDialog.vue";
import OrganizationChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/OrganizationChooseDialog.vue";
import PersonChooseDialog from "@lily/basic/src/net/bodz/lily/schema/contact/PersonChooseDialog.vue";

import DiaryChooseDialog from "./DiaryChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<DiaryParty>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = DiaryParty.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const diaryChooseDialog = ref<InstanceType<typeof DiaryChooseDialog>>();
const userChooseDialog = ref<InstanceType<typeof UserChooseDialog>>();
const personChooseDialog = ref<InstanceType<typeof PersonChooseDialog>>();
const organizationChooseDialog = ref<InstanceType<typeof OrganizationChooseDialog>>();
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
        <CoObjectFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_DiaryParty_stuff_TYPE">
            <FieldRow :property="meta.value" v-model="model.value">
                <input type="number" v-model="model.value" />
            </FieldRow>
            <FieldRow :property="meta.diary" v-model="model.diary">
                <RefEditor :dialog="diaryChooseDialog" v-model="model.diary" v-model:id="model.diaryId" />
            </FieldRow>
            <FieldRow :property="meta.user" v-model="model.user">
                <RefEditor :dialog="userChooseDialog" v-model="model.user" v-model:id="model.userId" />
            </FieldRow>
            <FieldRow :property="meta.person" v-model="model.person">
                <RefEditor :dialog="personChooseDialog" v-model="model.person" v-model:id="model.personId" />
            </FieldRow>
            <FieldRow :property="meta.org" v-model="model.org">
                <RefEditor :dialog="organizationChooseDialog" v-model="model.org" v-model:id="model.orgId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <DiaryChooseDialog ref="diaryChooseDialog" />
    <UserChooseDialog ref="userChooseDialog" />
    <PersonChooseDialog ref="personChooseDialog" />
    <OrganizationChooseDialog ref="organizationChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
