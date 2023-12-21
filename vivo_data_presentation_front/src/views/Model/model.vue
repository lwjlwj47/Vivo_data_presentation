<script setup>
import {useRouter} from "vue-router";
import models from "../../../docs/data/model.json"
const router = useRouter()
const url = "res/imgs/study.png"

const mymodels = models.model


const gotodetail = (model) => {
  router.push({
    path:"/Model/modeldetail",
    query:{
      name:model.name,
      desription:model.desription,
      arguments:JSON.stringify(model.arguments)
    }
  })
}
</script>

<template>
  <el-card class="vel_card_override">
  <div class="top">
    <div class="top-describe">
    <el-text class="top-model">模型</el-text>
      <el-text>此模型使用于Vivo OS度量评价系统</el-text>
    </div>
    <el-image :src="url" style="width: 250px;height: 250px"> </el-image>
  </div>
  <el-divider class="divider">
    <el-icon><star-filled /></el-icon>
  </el-divider>
    <el-row class="box" :gutter="24">
      <el-col :span="6" v-for="model in mymodels">
    <el-card class="box-card" shadow="hover" @click="gotodetail(model)">
      <template #header>
        <div class="card-header">
          <span>{{model.name}}</span>
        </div>
      </template>
      <div>
        <span>{{model.description}}</span>
      </div>
      <template #footer>Footer content</template>
    </el-card>
      </el-col>
    </el-row>
  </el-card>
</template>

<style scoped>
.vel_card_override{
  margin: 20px 20px 0px 20px;
  height: calc(100vh - 90px - 20px - 20px - 2px);
}
.top{
  display: flex;
  justify-content: space-between;
  padding: 30px 150px 30px 150px ;
  background-color: #ffffff;
}
.top-describe{
  display: flex;
  justify-content: center;
  flex-direction:column;
}
.top-model{
  font-size: 40px;
  font-family: 黑体;
  font-weight: bold;
  color: #1b1f23;
  margin-bottom: 10px;
}
.box{
  margin: 50px 1500px 50px 150px;
}
.box-card{
  height: 250px;
}
.card-header{
  font-family: "Adobe 黑体 Std R";
  font-size: 20px;
  font-weight: bold;
}
</style>