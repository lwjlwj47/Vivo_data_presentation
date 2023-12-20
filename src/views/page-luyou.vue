<template>
  <div class="page">
    <el-card shadow="never" class="vel_card_override">
      <EchartsAfterSalesMachineTypeLow/>
      <EchartsAfterSalesMachineTypeMid/>
      <EchartsAfterSalesMachineTypeHigh/>
    </el-card>
  </div>
</template>

<script setup>
const name = 'page-luyou'
import EchartsAfterSalesMachineTypeLow from "@/components/common/echarts-afterSales-machineType-low.vue";
import EchartsAfterSalesMachineTypeMid from "@/components/common/echarts-afterSales-machineType-mid.vue";
import EchartsAfterSalesMachineTypeHigh from "@/components/common/echarts-afterSales-machineType-high.vue";
</script>

<style scoped>
.page {
  padding: 20px;
}

.vel_card_override {
  height: calc(100vh - 90px - 20px - 20px - 2px);
}

</style>