<template>
  <div>
    <div class="forget_view">
      <el-form :model="forgetForm" class="forget_box">
        <div class="forget_title">
          基于springboot + vue的咖啡厅座位预约平台{{
            pageType == 1 ? '输入账号' : pageType == 2 ? '输入密保' : '重置密码'
          }}
        </div>
        <div class="tab_line">
          <div class="line"></div>
          <div class="num_line">
            <div
              class="line_number"
              :class="
                (pageType == 1 ? 'line_number1' : '',
                pageType > 1 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 2">1</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="
                (pageType == 2 ? 'line_number1' : '',
                pageType > 2 ? 'line_number2' : '')
              "
            >
              <div class="number" v-if="pageType < 3">2</div>
              <el-icon v-else><Check /></el-icon>
            </div>
            <div
              class="line_number"
              :class="pageType == 3 ? 'line_number1' : ''"
            >
              <div class="number">3</div>
            </div>
          </div>
        </div>
        <div class="list_item" v-if="pageType == 1">
          <div class="item_label">账号：</div>
          <input
            class="list_inp"
            v-model="forgetForm.username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="pageType == 2">
          <div class="item_label">密保问题：</div>
          <input
            class="list_inp question"
            v-model="userForm.pquestion"
            placeholder="请输入密保问题"
          />
        </div>
        <div class="list_item" v-if="pageType == 2">
          <div class="item_label">密保答案：</div>
          <input
            class="list_inp answer"
            v-model="forgetForm.myanswer"
            placeholder="请输入密保答案"
          />
        </div>
        <div class="list_item" v-if="pageType == 3">
          <div class="item_label">密码：</div>
          <input
            class="list_inp mima"
            v-model="forgetForm.mima"
            type="password"
            placeholder="请输入密码"
          />
        </div>
        <div class="list_item" v-if="pageType == 3">
          <div class="item_label">确认密码：</div>
          <input
            class="list_inp mima1"
            v-model="forgetForm.mima2"
            type="password"
            placeholder="请输入确认密码"
          />
        </div>
        <div class="list_btn">
          <el-button
            v-if="pageType == 1"
            class="get_btn"
            type="success"
            @click="getSecurity"
            >获取密保</el-button
          >
          <el-button
            v-if="pageType == 2"
            class="valid_btn"
            type="success"
            @click="validateSecurity"
            >确认密保</el-button
          >
          <el-button
            v-if="pageType == 3"
            class="update_btn"
            type="success"
            @click="updatePassword"
            >重置密码</el-button
          >
          <div class="r-login" @click="close">已有账号，直接登录</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const context = getCurrentInstance()?.appContext.config.globalProperties
onMounted(() => {})
const pageType = ref(1)
const forgetForm = ref({})
const userForm = ref({})
//获取用户信息
const getSecurity = () => {
  forgetForm.value.role = 'yonghu'
  if (!forgetForm.value.role) {
    context?.$toolUtil.message('请选择角色', 'error')
    return false
  }
  if (!forgetForm.value.username) {
    context?.$toolUtil.message('请输入账号', 'error')
    return false
  }
  context
    ?.$http({
      url:
        forgetForm.value.role +
        `/security?username=${forgetForm.value.username}`,
      method: 'get',
    })
    .then((res) => {
      if (res.data.data) {
        userForm.value = res.data.data
        pageType.value = 2
      } else {
        context?.$toolUtil.message('未获取到用户信息', 'error')
      }
    })
}
//验证
const validateSecurity = () => {
  if (userForm.value.panswer != forgetForm.value.myanswer) {
    context?.$toolUtil.message('答案输入不正确', 'error')
    return false
  }
  context?.$toolUtil.message('答案正确', 'success', () => {
    pageType.value = 3
  })
}
const updatePassword = () => {
  if (forgetForm.value.mima != forgetForm.value.mima2) {
    context?.$toolUtil.message('两次密码输入不一致', 'error')
    return false
  }
  if (forgetForm.value.role == 'yonghu') {
    userForm.value.mima = forgetForm.value.mima
  }
  context
    ?.$http({
      url: forgetForm.value.role + `/update`,
      method: 'post',
      data: userForm.value,
    })
    .then((res) => {
      context?.$toolUtil.message('修改密码成功', 'success', (obj) => {
        close()
      })
    })
}
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
</script>

<style lang="scss" scoped>
.forget_view {
  background-image: url('http://localhost:8080/coffeeShopSeatReserva/file/52cb1ed2781c4995ab0f9b6ea3176f00.webp') !important;
  // 表单盒子
  .forget_box {
    // 标题
    .forget_title {
    }
    // tab
    // 盒子
    .tab_line {
      // 中间线
      .line {
      }
      // item盒子
      .num_line {
        // 默认样式
        .line_number {
        }
        // 选中样式
        .line_number1 {
        }
        // 完成样式
        .line_number2 {
        }
      }
    }
    // item
    .list_item {
      // label
      .item_label {
      }
      // 输入框
      :deep(.list_inp) {
      }
    }
    // 按钮盒子
    .list_btn {
      // 获取密保
      :deep(.el-button--success) {
      }
      // 获取密保悬浮
      :deep(.el-button--success:hover) {
      }
      // 确认密保
      :deep(.el-button--primary) {
      }
      // 确认密保悬浮
      :deep(.el-button--primary:hover) {
      }
      // 重置密码
      :deep(.el-button--warning) {
      }
      // 重置密码悬浮
      :deep(.el-button--warning:hover) {
      }
      .r-login {
      }
    }
  }
}
</style>
<style lang="scss">
.forget_view {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url(http://localhost:8080/coffeeShopSeatReserva/file/52cb1ed2781c4995ab0f9b6ea3176f00.webp)
      no-repeat left center / 50% 100%,
    #5bbcb430;
  padding: 0 0 0 50%;
}

.forget_view .forget_box {
  width: 500px;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  border-radius: 10px;
  background: none;
  color: #000;
}

.forget_view .outTitle_view {
  display: flex;
  align-items: center;
  padding: 0px 0px 40px;
  margin: 0 50% 0 0;
}
.forget_view .outTitle_view .outTilte {
  font-size: 32px;
  font-weight: 600;
  text-align: center;
  background: -webkit-linear-gradient(
    left,
    #147b96,
    #7aa8c9 25%,
    #147b96 50%,
    #7aa8c9 75%,
    #147b96
  );
  color: transparent;
  -webkit-background-clip: text;
  background-size: 200% 100%;
  animation: masked-animation 1s infinite linear;
}

.forget_view .forget_box .tab_view {
  width: 100%;
  display: flex;
  align-items: center;
  padding: 0px 0px 20px;
}

.forget_view .forget_box .tab_view .tab {
  width: calc(33.3333%);
  padding: 16px 0px;
  box-sizing: border-box;
  text-align: center;
  font-size: 16px;
  background: #ccc;
  clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
}
.forget_view .forget_box .tab_view .tab.tab_active {
  width: calc(33.3333%);
  padding: 16px 0px;
  box-sizing: border-box;
  text-align: center;
  font-size: 16px;
  clip-path: polygon(0% 0%, 92% 0%, 100% 50%, 92% 100%, 0px 100%, 8% 50%);
  background: var(--theme);
  color: rgb(255, 255, 255);
}

.forget_view .forget_box .list_item {
  width: 100%;
  margin: 0px 0px 30px;
  background: none;
  border-radius: 8px;
}
.forget_view .forget_box .list_item .item_label {
  width: 220px;
  box-sizing: border-box;
  font-size: 16px;
  color: #000;
  line-height: 30px;
  letter-spacing: 6px;
}
.forget_view .forget_box .list_item .list_inp {
  height: 50px;
  line-height: 36px;
  border: none;
  border-bottom: 0px solid rgb(221, 221, 221);
  padding: 0px 10px;
  width: 100%;
  box-sizing: border-box;
  font-size: 15px;
  background: #fff;
  border-radius: 30px;
}
.forget_view .forget_box .list_item .list_sel {
  line-height: 50px;
  height: 50px;
  border: none;
  border-bottom: 0px solid rgb(221, 221, 221);
  box-sizing: border-box;
  width: 100%;
  padding: 0px 10px;
  background: #fff;
  border-radius: 30px;
}
.forget_view .forget_box .list_item .list_sel .el-input__inner {
  font-size: 15px;
  color: #666;
}

.forget_view .forget_box .list_btn {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  width: 100%;
  margin: 0px;
  padding: 0;
  text-align: center;
  justify-content: center;
  margin-top: 50px;
}

.forget_view .forget_box .list_btn .get_btn,
.forget_view .forget_box .list_btn .valid_btn,
.forget_view .forget_box .list_btn .update_btn {
  margin: 0;
  padding: 0px 24px;
  height: 50px;
  font-size: 20px;
  color: rgb(255, 255, 255);
  border: 0px;
  background: var(--theme);
  cursor: pointer;
  width: 100%;
  border-radius: 25px;
}

.forget_view .forget_box .list_btn .r-login {
  width: 100%;
  text-align: right;
  cursor: pointer;
  padding: 10px 0px 0px;
  font-size: 16px;
  color: #666;
}
.forget_view .forget_box .list_btn .r-login:hover {
  text-decoration: underline;
}

.forget_view .forget_title {
  font-size: 20px;
  text-align: center;
  margin-bottom: 40px;
  width: 100%;
  background: var(--theme);
  color: #fff;
  line-height: 60px;
  border-radius: 10px;
}

.forget_view .tab_line {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px 0px;
}

.forget_view .line {
  width: 100%;
  height: 2px;
  background: rgb(204, 204, 204);
}

.forget_view .num_line {
  margin: -20px 0px 0px;
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.forget_view .line_number {
  background: rgb(255, 255, 255);
  width: 40px;
  height: 40px;
  font-size: 22px;
  border-radius: 50%;
  border: 4px solid rgb(221, 221, 221);
  display: flex;
  align-items: center;
  justify-content: center;
  color: rgb(170, 170, 170);
}

.forget_view .line_number.line_number1 {
  background: rgb(255, 255, 255);
  width: 40px;
  height: 40px;
  font-size: 22px;
  border-radius: 50%;
  border-width: 4px;
  border-style: solid;
  border-color: var(--theme);
  border-image: initial;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--theme);
}
</style>
