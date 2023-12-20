<script setup>
import {ref} from "vue";
import * as echarts from 'echarts'
import VChart from "vue-echarts";
import axios from "axios";
const option = ref({
  title: {
    text: '不同手机售后反馈数量',
    subtext: '低端',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: '50%',
      data: [],
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
})
axios.get('http://localhost:8080/aftersalesfeedbacks/low/length/Type')
    .then(result=>{
      for( name in result.data.data ){
          option.value.series[0].data.push({"name":name,"value":result.data.data[name]})
      }
      console.log(option)


    })
    .catch(err=>{
      console.log(err)
    })

</script>

<template>
  <div style="height: 650px;width: 700px">
    <v-chart class="chart" :option="option" :autoresize="true"/>
  </div>
</template>

<style scoped>

</style>