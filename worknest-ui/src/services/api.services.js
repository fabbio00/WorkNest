import axios from 'axios';

class ApiService{

    find_occupied_desks(date){
        return axios.get('http://localhost:8080/bookings/findDesks?date=' + date).then((res) => {
            const desks = res.data.map(resource => resource.workStationId)
            return desks;
        });
    }

    find_desk_by_id(workStationId){
        return axios.get('http://localhost:8080/workstations/' + workStationId).then((res) => {
            return res;
        });
    }

    create_booking(booking){
        console.log(booking);
        return axios.post('http://localhost:8080/bookings', booking).then((res) => {
            return res;
        });
    }

    find_user_by_id(userId){
        return axios.get('http://localhost:8080/users/' + userId).then((res) => {
            return res;
        });
    }

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