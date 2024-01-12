<script setup>
import {useRouter} from "vue-router";
import models from "../../../docs/data/model.json"
import {Search} from "@element-plus/icons-vue";
const router = useRouter()
const url = "res/imgs/study.png"

const mymodels = models.model


const gotodetail = (model) => {
  router.push({
    path:"/Model/modeldetail",
    query:{
      name:model.name,
      description:model.description,
      descriptions:model.descriptions,
      arguments:JSON.stringify(model.arguments),
      outputs:JSON.stringify(model.outputs),
      datasum:JSON.stringify(model.datasum)
    }
  })
}
</script>

<template>
  <el-card class="vel_card_override">
    <div class="content">
  <div class="top">
    <div class="top-describe">
    <el-text class="top-model">模型</el-text>
      <el-text>此模型使用于Vivo OS度量评价系统</el-text>
    </div>
    <el-image :src="url" style="width: 250px;height: 250px"> </el-image>
  </div>
      <div>
        <el-input
            v-model="input2"
            class="search"
            placeholder="search model"
            :prefix-icon="Search"
        />
      </div>
      <div class="trend">
        <div class="trend-icon">
          <el-icon  size="25px" color="#1b1f23">
            <Star/>
          </el-icon>
          <div class="trend-word">
            Trending Models
          </div>
        </div>
        <el-button size="large" round text class="all-button">全部</el-button>
      </div>
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
      <template #footer>
        <div>Footer content
      </div>
      </template>
    </el-card>
      </el-col>
    </el-row>
    </div>
  </el-card>
</template>

<style scoped>
.vel_card_override{
  margin: 20px 20px 0px 20px;
  padding:0px 48px 0px 48px;
  height: calc(100vh - 90px - 20px - 20px - 2px);
}
.content{
  padding: 30px 150px 30px 150px;
}

.top{
  display: flex;
  justify-content: space-between;
  background-color: #ffffff;
}
.top-describe{
  display: flex;
  justify-content: center;
  flex-direction:column;
}
.search , .trend{
  height: 48px;
  margin:20px 0px 20px 0px;
  color: #1b1f23;
}
.trend{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.trend-icon{
  display: flex;
  flex-direction: row;
}
.trend-word{
  align-items: center;
  margin-left: 20px;
  font-size: 20px;
  font-weight: bold;
  line-height: 27px;
  font-family: sans-serif;
}
.all-button{
  font-family: 黑体;
  font-size: 18px;
  font-weight: bold;
}
:deep(.el-input__wrapper){
  border-radius: 20px !important;
}
.top-model{
  font-size: 40px;
  font-family: 黑体;
  font-weight: bold;
  color: #1b1f23;
  margin-bottom: 10px;
}
.box{
}
.box-card{
  height: 250px;
  border-radius:20px;
}
.card-header{
  font-family: "Adobe 黑体 Std R";
  font-size: 20px;
  font-weight: bold;
}
</style>