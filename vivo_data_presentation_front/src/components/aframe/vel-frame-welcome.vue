<script setup>
import httpUtil from "@/utils/HttpUtil";
import {useUserInfoStore} from "@/stores/data/userInfoStore";
import {computed} from "vue";
import EchartsDemo1 from '@/components/common/echarts-demo-1.vue'
import EchartsDemo2 from '@/components/common/echarts-demo-2.vue'
import EchartsDemo3 from '@/components/common/echarts-demo-3.vue'
import ProgressDemo1 from '@/components/common/progress-demo-1.vue'
import TableDemo1 from '@/components/common/table-demo-1.vue'

const userInfoStore = useUserInfoStore()

const githubUrl = httpUtil.defaults.baseURL
const avatarUrl = httpUtil.defaults.baseURL + '/res/imgs/jimeng.webp'

// 用户名称
const userNick = computed(() => userInfoStore.userInfo.userNick)

</script>

<template>
    <div class="welcome_panel">
        <el-row>
            <el-col :span="24">
                <el-card shadow="never" class="vel_el_card_override vel_el_card_introduce">
                    <div class="introduce_avatar">
                        <el-avatar :size="60" :src="avatarUrl"/>
                    </div>
                    <div class="introduce_desc">
                        <p class="tit">你好</p>
                        <p class="tips">
                          这里是Vivo OS产品度量评价系统
                        </p>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#409eff'">
                        <Eleme/>
                    </el-icon>
                    <p>主题1</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#60941f'">
                        <Headset/>
                    </el-icon>
                    <p>主题2</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#088f75'">
                        <IceDrink/>
                    </el-icon>
                    <p>主题3</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#cb8c1d'">
                        <IceTea/>
                    </el-icon>
                    <p>主题4</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#731f94'">
                        <Files/>
                    </el-icon>
                    <p>主题5</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#17a997'">
                        <Management/>
                    </el-icon>
                    <p>快主题6</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#1d310e'">
                        <Menu/>
                    </el-icon>
                    <p>主题7</p>
                </el-card>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3">
                <el-card shadow="hover" class="vel_el_card_override vel_el_card_button">
                    <el-icon :size="36" :color="'#b32eb6'">
                        <Message/>
                    </el-icon>
                    <p>主题8</p>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :xs="24" :sm="24" :md="12" :lg="7" :xl="7">
                <el-card shadow="hover" class="vel_el_card_override">
                    <template #header>
                        <div class="card-header">
                            <div class="vel_card_header_left">
                                <el-icon :size="20">
                                    <TrendCharts/>
                                </el-icon>
                                <span>访问量</span>
                            </div>
                            <div class="vel_card_header_right">
                                <el-tag>日</el-tag>
                            </div>
                        </div>
                    </template>
                    <echarts-demo1></echarts-demo1>
                </el-card>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="7" :xl="7">
                <el-card shadow="hover" class="vel_el_card_override">
                    <template #header>
                        <div class="card-header">
                            <div class="vel_card_header_left">
                                <el-icon :size="20">
                                    <Compass/>
                                </el-icon>
                                <span>浏览器</span>
                            </div>
                            <div class="vel_card_header_right">
                                <el-tag type="success">年度</el-tag>
                            </div>
                        </div>
                    </template>
                    <echarts-demo2></echarts-demo2>
                </el-card>
            </el-col>
            <el-col :xs="24" :sm="24" :md="24" :lg="10" :xl="10">
                <el-card shadow="hover" class="vel_el_card_override">
                    <template #header>
                        <div class="card-header">
                            <div class="vel_card_header_left">
                                <el-icon :size="20">
                                    <InfoFilled/>
                                </el-icon>
                                <span>软件信息</span>
                            </div>
                            <div class="vel_card_header_right">
                                <el-tag type="info">V 2.0.0</el-tag>
                            </div>
                        </div>
                    </template>
                    <table-demo1></table-demo1>
                </el-card>
            </el-col>
        </el-row>
        <el-row>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
                <el-card shadow="hover" class="vel_el_card_override">
                    <template #header>
                        <div class="card-header">
                            <div class="vel_card_header_left">
                                <el-icon :size="20">
                                    <MapLocation/>
                                </el-icon>
                                <span>用户分布</span>
                            </div>
                            <div class="vel_card_header_right">
                                <el-tag type="danger">不遗漏任何一个角落</el-tag>
                            </div>
                        </div>
                    </template>
                    <echarts-demo3></echarts-demo3>
                </el-card>
            </el-col>
            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
                <el-card shadow="hover" class="vel_el_card_override">
                    <template #header>
                        <div class="card-header">
                            <div class="vel_card_header_left">
                                <el-icon :size="20">
                                    <List/>
                                </el-icon>
                                <span>任务清单</span>
                            </div>
                            <div class="vel_card_header_right">
                                <el-tag type="info">日</el-tag>
                            </div>
                        </div>
                    </template>
                    <progress-demo1></progress-demo1>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<style scoped>
.welcome_panel {
    padding: 20px 0 0 20px;
}

:deep(.vel_el_card_introduce) .el-card__body {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    justify-content: center;
}

:deep(.vel_el_card_override) .el-card__header {
    padding-top: 15px;
    padding-bottom: 15px;
}

.introduce_avatar .el-avatar {
    margin-right: 20px;
}

.introduce_desc {
    width: calc(100% - 80px);
    min-width: 200px;
}

.introduce_desc .tit {
    margin: 20px 0 15px 0;
    color: #141b1e;
    font-weight: 400;
    font-size: 20px;
}

.introduce_desc .tips {
    color: #808695;
}

.el-row .el-col {
    padding-bottom: 20px;
    padding-right: 20px;
}

.vel_el_card_override .card-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.vel_el_card_override .vel_card_header_left {
    display: flex;
    align-items: center;
}

.vel_el_card_override .vel_card_header_left .el-icon {
    margin-right: 5px;
}

:deep(.vel_el_card_button) .el-card__body {
    display: flex;
    flex-direction: column;
    align-items: center;
}

:deep(.vel_el_card_button) .el-card__body p {
    padding-top: 15px;
}

</style>
