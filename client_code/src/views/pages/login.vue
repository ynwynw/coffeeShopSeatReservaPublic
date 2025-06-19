<template>
  <div>
    <div class="login_view">
      <el-form :model="loginForm" class="login_form">
        <div class="title_view">
          基于springboot + vue的咖啡厅座位预约平台登录
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">账号：</div>
          <input
            class="list_inp"
            v-model="loginForm.username"
            name="username"
            placeholder="请输入账号"
          />
        </div>
        <div class="list_item" v-if="loginType == 1">
          <div class="list_label">密码：</div>
          <input
            class="list_inp"
            v-model="loginForm.password"
            type="password"
            placeholder="请输入密码"
            @keydown.enter.native="handleLogin"
          />
        </div>
        <div class="list_type" v-if="userList.length > 1">
          <el-radio
            v-for="(item, index) in userList"
            :key="index"
            v-model="loginForm.role"
            :label="item.roleName"
            >{{ item.roleName }}</el-radio
          >
        </div>
        <div class="remember_view" v-if="loginType == 1">
          <el-checkbox
            v-model="rememberPassword"
            label="记住密码"
            size="large"
            :true-label="true"
            :false-label="false"
          />
        </div>
        <div class="forget-wrapper">
          <el-button class="forget" @click="handleForget">忘记密码</el-button>
        </div>
        <div class="login-wrapper">
          <el-button class="login" v-if="loginType == 1" @click="handleLogin"
            >登录</el-button
          >
        </div>
        <div class="face-wrapper"></div>
        <div class="register-wrapper">
          <el-button class="register" @click="handleRegister('yonghu')"
            >注册用户</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick, onMounted } from 'vue'
import { useStore } from 'vuex'
const store = useStore()
import menu from '@/utils/menu'
const userList = ref([])
const menus = ref([])
const loginForm = ref({
  role: '',
  username: '',
  password: '',
})
const tableName = ref('')
const loginType = ref(1)
//是否记住密码
const rememberPassword = ref(false)
const context = getCurrentInstance()?.appContext.config.globalProperties
//注册
const handleRegister = (tableName) => {
  context?.$router.push(`/${tableName}Register`)
}
const handleLogin = () => {
  if (!loginForm.value.username) {
    context?.$toolUtil.message('请输入用户名', 'error')
    return
  }
  if (!loginForm.value.password) {
    context?.$toolUtil.message('请输入密码', 'error')
    return
  }
  if (userList.value.length > 1) {
    if (!loginForm.value.role) {
      context?.$toolUtil.message('请选择角色', 'error')
      verifySlider.reset()
      return
    }
    for (let i = 0; i < menus.value.length; i++) {
      if (menus.value[i].roleName == loginForm.value.role) {
        tableName.value = menus.value[i].pathName || menus.value[i].tableName
      }
    }
  } else {
    tableName.value = userList.value[0].pathName || userList.value[0].tableName
    loginForm.value.role = userList.value[0].roleName
  }
  login()
}
const login = () => {
  context
    ?.$http({
      url: `${tableName.value}/login?username=${loginForm.value.username}&password=${loginForm.value.password}`,
      method: 'post',
    })
    .then(
      (res) => {
        //是否保存当前账号密码至缓存
        if (rememberPassword.value) {
          let loginForm1 = JSON.parse(JSON.stringify(loginForm.value))
          delete loginForm1.code
          context?.$toolUtil.storageSet(
            'frontLoginForm',
            JSON.stringify(loginForm1)
          )
        } else {
          context?.$toolUtil.storageRemove('frontLoginForm')
        }
        context?.$toolUtil.storageSet('frontToken', res.data.token)
        context?.$toolUtil.storageSet('frontRole', loginForm.value.role)
        context?.$toolUtil.storageSet('frontSessionTable', tableName.value)
        store.dispatch('user/getSession')
        let path = context?.$toolUtil.storageGet('toPath')
        if (path && path != '/login') {
          context?.$router.push(path)
          context?.$toolUtil.storageRemove('toPath')
          return
        }
        context?.$router.push(`/index/${tableName.value}Center`)
      },
      (err) => {}
    )
}
//忘记密码
const handleForget = () => {
  context?.$router.push({
    name: 'forget',
  })
}
//获取菜单
const getMenu = async () => {
  let arr = menu.list()
  if (!arr) {
    let res = await context?.$http.get('menu/list')
    context?.$toolUtil.storageSet('menus', res.data.data.list[0].menujson)
    arr = JSON.parse(res.data.data.list[0].menujson)
  }
  menus.value = arr
  for (let i = 0; i < menus.value.length; i++) {
    if (menus.value[i].hasFrontLogin == '是') {
      userList.value.push(menus.value[i])
    }
  }
}
//初始化
const init = async () => {
  await getMenu()
  //获取缓存是否有保存的账号密码
  let form = context?.$toolUtil.storageGet('frontLoginForm')
  if (form) {
    loginForm.value = JSON.parse(form)
    rememberPassword.value = false
  } else {
    loginForm.value.role = userList.value[0].roleName
  }
}
onMounted(() => {
  init()
})
</script>

<style lang="scss" scoped>
.login_view {
  background-image: url('http://localhost:8080/coffeeShopSeatReserva/file/52cb1ed2781c4995ab0f9b6ea3176f00.webp') !important;
  // 表单盒子
  .login_form {
  }
  .title_view {
  }
  // item盒子
  .list_item {
    // label
    .list_label {
    }
    // 输入框
    .list_inp {
    }
  }
  // 用户类型样式
  .list_type {
    // 未选中样式
    :deep(.el-radio) {
      // 单选框
      .el-radio__inner {
      }
      // 提示文字
      .el-radio__label {
      }
    }
    // 选中样式
    :deep(.is-checked) {
      // 单选框
      .el-radio__inner {
      }
      // 提示文字
      .el-radio__label {
      }
    }
  }
  // 记住密码样式
  .remember_view {
    width: 100%;
    text-align: center;
    order: 3;

    // 未选中样式
    :deep(.el-checkbox) {
      margin: 0;
      display: flex;
      width: 20%;
      justify-content: center;
      align-items: center;
      // 复选框
      .el-checkbox__inner {
        border: 1px solid #dcdfe6;
        background: #fff;
      }
      // 提示文字
      .el-checkbox__label {
        color: #999;
        font-size: 16px;
      }
    }
    // 选中样式
    :deep(.is-checked) {
      //复选框
      .el-checkbox__inner {
        background-color: var(--theme);
        border-color: var(--theme);
      }
      // 提示文字
      .el-checkbox__label {
        color: var(--theme);
        font-size: 16px;
      }
    }
  }
  // 按钮盒子
  .btn_view {
    // 登录
    .login {
    }
    // 注册
    .register {
    }
    // 忘记密码
    .forget {
    }
  }
}
</style>
<style lang="scss">
.login_view {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: url(http://localhost:8080/coffeeShopSeatReserva/file/52cb1ed2781c4995ab0f9b6ea3176f00.webp)
      no-repeat left center / 50% 100%,
    #5bbcb430;
  padding: 0 0 0 50%;
}

.login_view form.el-form.login_form {
  background: none;
  width: 500px;
  row-gap: 10px;
  display: flex;
  flex-wrap: wrap;
  background-size: 400px 700px;
  background-repeat: no-repeat;
  background-position: right;
  color: #000;
  justify-content: center;
  border-radius: 0;
  position: relative;
  align-items: center;
  align-content: center;
  font-size: 16px;
}

.login_view .title_view {
  width: 100%;
  order: 1;
  font-size: 24px;
  text-align: center;
  background: var(--theme);
  color: #fff;
  line-height: 60px;
  border-radius: 10px;
  font-size: 20px;
}

.login_view .tabView {
  order: 2;
  display: flex;
  width: 100%;
  height: 50px;
  border-radius: 6px;
  overflow: hidden;
}

.login_view .tab {
  line-height: 50px;
  height: 50px;
  background: #e2e2e2;
  color: #959595;
  text-align: center;
  font-weight: 600;
}

.login_view .tab.tabActive {
  background: var(--theme);
  color: #fff;
}

.login_view .list_item {
  order: 3;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  background: none;
  align-items: center;

  border-radius: 6px;
  position: relative;
}

.login_view .listCode_view {
  order: 4;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  background: none;

  border-radius: 6px;
  position: relative;

  width: 100%;
}

.login_view .face {
  order: 5;
  cursor: pointer;
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  line-height: 50px;
  text-align: center;
  background: var(--theme);
  border-radius: 10px;
}

.remember_view {
  margin: 10px auto;
}

.login_view .btn_view {
  order: 5;
  position: relative;
  margin-top: 40px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
}

.login_view .list_label {
  color: var(--theme);
  width: 100%;
  position: relative;
  z-index: 1;
  padding-left: 10px;
  margin-bottom: 10px;
}

.login_view input.list_inp {
  flex: 1;
  border: none;
  line-height: 48px;
  padding-left: 10px;
  border: 1px solid #c7c7c7;
  border-radius: 10px;
}

.login_view .listCode_label {
  width: 100%;
  color: var(--theme);
  flex-shrink: 0;
  position: relative;
  z-index: 0;
  padding-left: 10px;
  margin-bottom: 10px;
}

.login_view input.listCode_inp {
  flex: 1;
  line-height: 48px;
  width: 145px;
  padding: 0 10px;
  padding-right: 120px;
  border: 1px solid #c7c7c7;
  border-radius: 10px;
}

.login_view .listCode_btn {
  height: 50px;
  padding: 0 10px;
  display: flex;
  align-items: center;
  width: 110px;
  border-radius: 0;
  background: var(--theme);
  justify-content: center;
  border-radius: 10px;
  position: absolute;
  right: 0;
  bottom: 0;
}

.login_view .el-button.login {
  border: none;
  width: 100%;
  border-radius: 0;
  height: 50px;
  background: var(--theme);
  color: #fff;
  font-size: 18px;
  font-weight: 700;
  border-radius: 10px;
}

.login_view .el-button.forget {
  width: 100px;
  background: none;
  border: none;
  color: inherit;
}
.login_view .el-button.forget span {
  font-size: 16px;
}

.login_view button.el-button.register {
  height: 50px;
  flex: 1;
  border: 1px solid var(--theme);
  border-radius: 10px;
  color: #fff;
  margin: 0;
  background: var(--theme);
  font-size: 18px;
}

.login_view .list_type {
  order: 5;
  display: flex;
  height: 50px;
  align-items: center;
  width: 100%;
  border-radius: 6px;
  border: 0px solid var(--theme);
  justify-content: flex-start;
  column-gap: 30px;
  flex-wrap: wrap;
  background: none;
}
.login_view .list_type .el-radio__label {
  font-size: 16px;
}

.login_view .list_type .el-select {
  background: #fff;
}

.login_view .el-select {
  flex: 1;
  line-height: 48px;
  border: 1px solid rgba(199, 199, 199, 1) !important;
  color: #000;

  border-radius: 10px;
  padding: 0 10px;
  height: 50px;
}

.login_view .forget-wrapper {
  order: 6;
  width: 100%;
  text-align: center;
  color: rgba(94, 94, 94, 1);
}

.login_view .login-wrapper {
  order: 7;
  width: 100%;
}

.login_view .face-wrapper {
  order: 8;
  width: 100%;
}

.login_view .register-wrapper {
  order: 9;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  column-gap: 20px;
}

.login_view .el-radio__input.is-checked .el-radio__inner {
  background: var(--theme);
  border-color: var(--theme);
}

.login_view .el-radio__input.is-checked + .el-radio__label {
  color: var(--theme);
  font-size: 16px;
}

.login_view .el-radio__inner:hover {
  border-color: var(--theme);
}
</style>
