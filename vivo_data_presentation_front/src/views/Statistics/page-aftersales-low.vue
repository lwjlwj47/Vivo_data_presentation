<template>
  <div class="page">
    <el-card class = "top">
      <div class="top-line">这里是舆情反馈分析-高端系列</div>
      <div class="top-number">数据总数量：102391</div>
    </el-card>
    <el-row :gutter="24">
      <el-col :span="10">
        <el-card shadow="always">
          <EchartsAfterSalesMachineTypeLow/>
        </el-card>
      </el-col>
      <el-col :span="14">
        <el-card shadow="hover">
          <EchartsAfterSalesCategoryLow/>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
const name = 'page-publicopinion-low'
import EchartsAfterSalesCategoryLow from "@/components/common/echarts-afterSales-category-low.vue";
import EchartsAfterSalesMachineTypeLow from "@/components/common/echarts-afterSales-machineType-low.vue";
</script>

<style scoped>
.page {
  padding: 20px;
}
.top{
  margin: 20px 0px 20px 0px;
}
.top-line{
  font-family: "Adobe 黑体 Std R";
  font-size: 20px;
  margin-bottom: 10px;
}


/*.vel_card_override {
  height: calc(100vh - 90px - 20px - 20px - 2px);
//display: flex !important;
//align-items: center;
//justify-content: space-between;
}*/

</style>