<!--
 * 注册组件
 * @author: wrp
 * @since: 2024年11月23日 12:55:05
-->
<template>
    <div class="login-container">
        <el-dialog v-model="navStore.state.showRegisterDialog" title="注册账号" width="600" :show-close='false'>
            <el-form ref="registerFormRef" style="max-width: 600px" :model="registerForm" status-icon :rules="rules"
                label-width="auto" class="demo-registerForm">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="registerForm.username" autocomplete="off" />
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input v-model="registerForm.password" type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="registerForm.phone" autocomplete="off" />
                </el-form-item>
                <el-form-item label="生日" prop="birthday">
                    <el-date-picker v-model="registerForm.birthday" type="date" placeholder="选择一个日期" format="YYYY-MM-DD"
                        value-format="YYYY-MM-DD" />
                </el-form-item>
                <el-form-item label="自我介绍" prop="introduction">
                    <el-input v-model="registerForm.introduction" type="textarea" autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <div class="register">
                        <el-button type="primary" @click="submitForm(registerFormRef)">
                            注册
                        </el-button>
                        <el-button @click="navStore.changeRegisterDialogStatus">取消</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useNavStore } from '../stores/useNavStore';
import type { FormInstance, FormRules } from 'element-plus'
import httpUtils from '../utils/httpUtils';

const registerFormRef = ref<FormInstance>()
const navStore = useNavStore()


const validatePassword = (rule: any, value: any, callback: any) => {
    debugger
    if (value === '') {
        callback(new Error('Please input the password'))
    } else {
        if (registerForm.password !== '') {
            if (!registerFormRef.value) return
            registerFormRef.value.validateField('checkPass')
        }
        callback()
    }
}

const registerForm = reactive({
    username: '',
    password: '',
    phone: '',
    birthday: null,
    introduction: '',
    photoId: ''
})

const rules = reactive<FormRules<typeof registerForm>>({
    password: [{ validator: validatePassword, trigger: 'blur' }],
})

const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
        if (valid) {
            // 发送http请求
            // 关闭dialog
            const data = httpUtils.post("/user/register", registerForm)
            console.log(data)
            navStore.changeRegisterDialogStatus();
        } else {
            console.log('error submit!')
        }
    })
}

</script>

<style scoped>
.register {
    margin: 0 auto;
}
</style>
