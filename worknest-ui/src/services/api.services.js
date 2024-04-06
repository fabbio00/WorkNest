import axios from 'axios';

class ApiService{

    create_user(user){
        return axios.post("http://localhost:8080/users", user).then((res) => {
            return res;
        });
    }
}

export default new ApiService();