import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/SpringMVCClinicManagement";
const SERVER = "http://localhost:8080";
const user = cookie.load("user")

export const endpoints = {
    "appointments": `${SERVER_CONTEXT}/api/appointments/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "medicines": `${SERVER_CONTEXT}/api/medicines/`,
    "rescriptions-medicines": `${SERVER_CONTEXT}/api/rescriptions-medicines/`,
    "prescription": `${SERVER_CONTEXT}/api/prescriptions/`,
    "rules": `${SERVER_CONTEXT}/api/rules/`,
    "payments": `${SERVER_CONTEXT}/api/payments/`,
    "shifts": `${SERVER_CONTEXT}/api/shifts/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "check-username": `${SERVER_CONTEXT}/api/check-username/`,
    "check-email": `${SERVER_CONTEXT}/api/check-email/`
}

export const authApi = () => {
    return axios.create({
        baseURL:SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})