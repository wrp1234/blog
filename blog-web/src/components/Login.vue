<!--
 * 登录组件
 * @author: wrp
 * @since: 2024年11月23日 12:55:05
-->
<template>
    <div class="login-container">
        <el-dialog v-model="navStore.state.showLoginDialog" title="账号登录" width="600" :show-close='false'>
            <el-form ref="loginFormRef" style="max-width: 600px" :model="loginForm" status-icon :rules="rules"
                label-width="auto" class="demo-ruleForm">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="loginForm.username" autocomplete="off" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="loginForm.password" type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item label="验证码" prop="code">
                    <el-input v-model="loginForm.code" autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <div class="login">
                        <el-button type="primary" @click="submitForm(loginFormRef)">
                            登录
                        </el-button>
                        <el-button @click="navStore.changeLoginDialogStatus">取消</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import { useNavStore } from '../stores/useNavStore';
import type { FormInstance, FormRules } from 'element-plus'
import uuid from '../utils/uuid'
import httpUtils from '../utils/httpUtils';

const loginFormRef = ref<FormInstance>()
const navStore = useNavStore()

onMounted(async () => {
    const key = uuid()
    loginForm.key = key
})

const validateUsername = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else {
        callback()
    }
}

const validatePassword = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入密码'))
    } else {
        callback()
    }
}

const loginForm = reactive({
    username: '',
    password: '',
    key: '',
    code: ''
})

const rules = reactive<FormRules<typeof loginForm>>({
    username: [{ validator: validateUsername, trigger: 'blur' }],
    password: [{ validator: validatePassword, trigger: 'blur' }],
})

const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate(async (valid) => {
        if (valid) {
            const data = await httpUtils.post('/user/login/pwd', loginForm)
            console.log(data)
            navStore.changeLoginDialogStatus()
        } else {
            console.log('error submit!')
        }
    })
}

</script>

<style scoped>
.login {
    margin: 0 auto;
}
</style>
