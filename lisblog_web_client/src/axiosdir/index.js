import axios from "axios";
import router from "../router";
import store from "../store";
import { ElMessage } from 'element-plus'

const http = axios.create({
    baseURL: 'http://127.0.0.1:5173',
    timeout: 15 * 1000
})

http.interceptors.request.use(config => {
    let needToken = (config.headers || {}).isToken;
    if(needToken) {
        console.log("需要token ", store.getters.getToken)
    } else {
        console.log("不需要token " )
    }
    if (store.getters.getToken && needToken) {
        config.headers['token'] = store.getters.getToken // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
})

http.interceptors.response.use(
    response => {
    const res = response.data;
    console.log("后置拦截")
    if(res.code == 200) {
        return response;
    } else {
        console.log('res.code != 200  ' , res)
        ElMessage.error(res.message)
        return Promise.reject(res.message)
    }
    },
    error => {
        console.log('error  ' + error)
        if(error.response.data) {
            error.message = error.response.data.msg
        }
        //根据请求状态觉得是否登录或者提示其他
        if(error.response.status == 401) {
            store.commit('REMOVE_INFO');
            router.push({
                path: '/login'
            });
            error.message = '请重新登录'
        }
        if(error.response.status == 403) {
            error.message = '权限不足，无法访问';
        }
        ElMessage.error(error.message)
        return Promise.reject(error);
    }
)

export default http 