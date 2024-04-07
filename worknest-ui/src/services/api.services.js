import axios from 'axios';

class ApiService{
    login(email, password) {
        return axios.post("http://localhost:8080/users/login", {
            email: email,
            password: password
        }).then((res) => {
            return res;
        }).catch((err)=>{
            localStorage.removeItem('userId');
            return "unauthorized";
        });
    }

    create_user(user){
        return axios.post("http://localhost:8080/users", user).then((res) => {
            return res;
        });
    }
}

export default new ApiService();