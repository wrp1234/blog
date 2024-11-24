import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { ElMessage } from "element-plus";
import { Result, UserInfo } from "../types";



// console.log('import.meta.env: ', import.meta.env);

// 创建 axios 实例
let httpUtils: AxiosInstance | any;
if (import.meta.env.MODE === "development") {
    httpUtils = axios.create({
        baseURL: 'http://localhost:8080', // api 的 base_url
        timeout: 1000 // 请求超时时间
    });
} else {
    // 生产环境下
    httpUtils = axios.create({
        baseURL: "http://localhost:8080",
        timeout: 1000
    });
}

// request 拦截器 axios 的一些配置
httpUtils.interceptors.request.use(
    (config: AxiosRequestConfig) => {
        if (window.localStorage.userInfo) {
            const userInfo: UserInfo = JSON.parse(window.localStorage.userInfo);
            if (config && config.headers) {
                config.headers.Authorization = userInfo.token
            }
        }

        return config
    },
    (error: any) => {
        // Do something with request error
        console.error("error:", error) // for debug
        Promise.reject(error)
    }
);

// respone 拦截器 axios 的一些配置
httpUtils.interceptors.response.use(
    (res: AxiosResponse) => {
        // Some example codes here:
        // code == 0: success
        if (res.status === 200) {
            const data: Result = res.data
            if (data.code === 10000) {
                return data.data;
            } else {
                ElMessage({
                    message: data.message,
                    type: "error"
                });
            }
        } else {
            ElMessage({
                message: "网络错误!",
                type: "error"
            });
            return Promise.reject(new Error(res.data.message || "Error"));
        }
    },
    (error: any) => Promise.reject(error)
);

export default httpUtils;
