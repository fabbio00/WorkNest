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

}

export default new ApiService();