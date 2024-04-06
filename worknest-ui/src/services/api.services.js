import axios from 'axios';

class ApiService{
    login(email, password) {
        return axios.post("http://localhost:8080/users/login", {
            email: email,
            password: password
        }).then((res) => {
            localStorage.setItem('user', res.data);
            return res;
        })
    }
}

export default new ApiService();