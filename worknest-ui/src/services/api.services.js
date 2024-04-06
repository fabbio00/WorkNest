import axios from 'axios';

class ApiService{
    login(email, password) {
        return axios.post("http://localhost:8080/users/login", {
            email: email,
            password: password
        }).then((res) => {
            if(res.data && res.data.id){
                const expirationTime = Date.now() + (3* 3600 * 1000); // 3 ore di durata della sessione
                localStorage.setItem('expirationTime', expirationTime);
                localStorage.setItem('userId', res.data.id);
            }
            return res;
        }).catch((err)=>{
            localStorage.removeItem('userId');
            return "unauthorized";
        });
    }
}

export default new ApiService();