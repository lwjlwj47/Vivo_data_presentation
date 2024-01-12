<script setup>
import {ref} from "vue";
import {prop} from "dom7";
const props = defineProps(['propDate'])
// const Myarguments = ref(props.propDate)
const Mymodel = props.propDate
console.log(Mymodel)
</script>

<template>
<div class = "top-name">
  Model Details
</div>
  <el-card class="detail">
    <div class="name" style="margin-top: 0px">
      Introduction
    </div>
  <div>
    {{Mymodel.descriptions}}
  </div>
  <div class="name">
    Arguments
  </div>
  <li v-for="argument in JSON.parse(Mymodel.arguments)" class="list">
  <span class="argumentname">{{argument.argument}}:</span><span>{{argument.desriber}}</span>
  </li>
  <div class = "name">
    output
  </div>
  <li v-for="output in JSON.parse(Mymodel.outputs)" class="list">
    <span class="argumentname">{{output.output}}:</span><span>{{output.desriber}}</span>
  </li>
  </el-card>
  <div class="top-name">
    Model Datasum
  </div>
  <el-card class="data">
    <li v-for="data in JSON.parse(Mymodel.datasum)" class="list">
      <span class="argumentname">{{data.data}}:</span><span>{{data.describe}}</span>
    </li>
  </el-card>
</template>

<style scoped>
.top-name{
  font-size: 30px;
  margin:30px 0px 30px 0px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.name{
  font-size: 20px;
  margin:30px 0px 10px 0px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.argumentname{
  font-size: 17px;
  margin-top:30px;
  font-family: "Adobe 黑体 Std R";
  font-weight: bold;
}
.list{
  margin: 20px 0px 20px 0px;
}
.detail ,.data{
  border-radius:20px
}

</style>