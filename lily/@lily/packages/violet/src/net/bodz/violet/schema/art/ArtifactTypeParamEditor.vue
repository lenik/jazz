<script lang="ts">
import { onMounted, provide, ref } from "vue";

import type { double, int } from "skel01-core/src/lang/basetype";
import { getDefaultFieldRowProps } from "skel01-dba/src/ui/lily/defaults";
import { IdEntity_TYPE } from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import ArtifactTypeParam from "./ArtifactTypeParam";
import { _ArtifactTypeParam_stuff_TYPE } from "./_ArtifactTypeParam_stuff_TypeInfo";

export const title = "Editor view of: Artifact type param";
export interface Props {
}

</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import { FIELD_ROW_PROPS } from "skel01-core/src/ui/FieldRow.vue";
import RefEditor from "skel01-dba/src/ui/input/RefEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";
import StructRowFieldGroup from "lily-basic/src/net/bodz/lily/concrete/StructRowFieldGroup.vue";

import ArtifactTypeChooseDialog from "./ArtifactTypeChooseDialog.vue";
import StdParameterChooseDialog from "./StdParameterChooseDialog.vue";

defineOptions({
    inheritAttrs: false
});

const model = defineModel<ArtifactTypeParam>();

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
    change: [e: Event]
}>();

// property shortcuts

const meta = ArtifactTypeParam.TYPE.property;
const fieldRowProps = getDefaultFieldRowProps({ labelWidth: '7rem' });
provide(FIELD_ROW_PROPS, fieldRowProps);

const rootElement = ref<HTMLElement>();
const artifactTypeChooseDialog = ref<InstanceType<typeof ArtifactTypeChooseDialog>>();
const stdParameterChooseDialog = ref<InstanceType<typeof StdParameterChooseDialog>>();
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
        <StructRowFieldGroup :meta="meta" v-model="model" />
        <FieldGroup :type="IdEntity_TYPE">
            <FieldRow :property="meta.id" v-model="model.id">
                <input type="number" v-model="model.id" disabled />
            </FieldRow>
        </FieldGroup>
        <FieldGroup :type="_ArtifactTypeParam_stuff_TYPE">
            <FieldRow :property="meta.ival" v-model="model.ival">
                <input type="number" v-model="model.ival" />
            </FieldRow>
            <FieldRow :property="meta.fval" v-model="model.fval">
                <input type="number" v-model="model.fval" />
            </FieldRow>
            <FieldRow :property="meta.sval" v-model="model.sval">
                <input type="text" v-model="model.sval" />
            </FieldRow>
            <FieldRow :property="meta.arttype" v-model="model.arttype">
                <RefEditor :dialog="artifactTypeChooseDialog" v-model="model.arttype" v-model:id="model.arttypeId" />
            </FieldRow>
            <FieldRow :property="meta.parameter" v-model="model.parameter">
                <RefEditor :dialog="stdParameterChooseDialog" v-model="model.parameter" v-model:id="model.parameterId" />
            </FieldRow>
        </FieldGroup>
    </div>
    <ArtifactTypeChooseDialog ref="artifactTypeChooseDialog" />
    <StdParameterChooseDialog ref="stdParameterChooseDialog" />
</template>

<style scoped lang="scss">
.entity-editor {
    padding: 0;
}
</style>
