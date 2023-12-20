<template>
  <div class="page">
    <el-card  class="vel_card_override">
      <EchartsOpinionMachineTypeLow/>
      <EchartsOpinionCategoryLow/>
    </el-card>
  </div>
</template>

<script setup>
const name = 'page-jieshao'
import EchartsOpinionMachineTypeLow from "@/components/common/echarts-Opinion-machineType-low.vue";
import EchartsOpinionMachineTypeMid from "@/components/common/echarts-Opinion-machineType-mid.vue";
import EchartsOpinionMachineTypeHigh from "@/components/common/echarts-Opinion-machineType-high.vue";
import EchartsOpinionCategoryHigh from "@/components/common/echarts-Opinion-category-high.vue";
import EchartsOpinionCategoryMid from "@/components/common/echarts-Opinion-category-mid.vue";
import EchartsOpinionCategoryLow from "@/components/common/echarts-Opinion-category-low.vue";
</script>

<style scoped>
.page {
  padding: 20px;
}

.vel_card_override {
  height: calc(100vh - 90px - 20px - 20px - 2px);
  //display: flex !important;
  //align-items: center;
  //justify-content: space-between;
}

</style>