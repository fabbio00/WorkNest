import axios from "axios";

class ApiService {
  find_occupied_desks(date) {
    return axios
      .get("http://localhost:8080/bookings/findDesks?date=" + date)
      .then((res) => {
        return res;
      });
  }

  find_desk_by_id(workStationId) {
    return axios
      .get("http://localhost:8080/workstations/" + workStationId)
      .then((res) => {
        return res;
      });
  }

  create_booking(booking) {
    return axios.post("http://localhost:8080/bookings", booking).then((res) => {
      return res;
    });
  }

  find_booking_by_id(bookingId) {
    return axios
      .get("http://localhost:8080/bookings/" + bookingId)
      .then((res) => {
        return res;
      });
  }

  find_user_by_id(userId) {
    return axios.get("http://localhost:8080/users/" + userId).then((res) => {
      return res;
    });
  }

  login(email, password) {
    return axios
      .post("http://localhost:8080/users/login", {
        email: email,
        password: password,
      })
      .then((res) => {
        return res;
      })
      .catch((err) => {
        localStorage.removeItem("userId");
        return "unauthorized";
      });
  }

  create_user(user) {
    return axios.post("http://localhost:8080/users", user).then((res) => {
      return res;
    });
  }

  get_list_booking(userId) {
    return axios
      .get("http://localhost:8080/bookings/list/" + userId)
      .then((res) => {
        return res;
      });
  }

  delete_booking(bookingId) {
    return axios
      .put("http://localhost:8080/bookings/delete/" + bookingId)
      .then((res) => {
        return res;
      });
  }

  send_mail(email) {
    return axios.post("http://localhost:8080/sendEmail", email).then((res) => {
      return res;
    });
  }

  modify_booking(bookingId, newBooking) {
    return axios
      .put("http://localhost:8080/bookings/edit/" + bookingId, newBooking)
      .then((res) => {
        return res;
      });
  }

  create_company(company) {
    return axios
      .post("http://localhost:8080/companies", company)
      .then((res) => {
        return res;
      });
  }

  get_list_employee(companyId) {
    return axios.get("http://localhost:8080/users/company/" + companyId)
      .then((res) => {
        return res;
      });
  }

  delete_user(userId) {
    return axios.put("http://localhost:8080/users/status/" + userId)
      .then((res) => {
        return res;
      });
  }

  edit_user_type(userId, userType) {
    return axios.put("http://localhost:8080/users/type/" + userId, userType)
      .then((res) => {
        return res;
      });
  }

  get_list_by_company_booking(companyId, employeeName = '', employeeSurname = '', startDate = null, endDate = null) {
    const params = new URLSearchParams();
    if (employeeName) params.append('employeeName', employeeName);
    if (employeeSurname) params.append('employeeSurname', employeeSurname);
    if (startDate) params.append('startDate', startDate);
    if (endDate) params.append('endDate', endDate);
    return axios
      .get("http://localhost:8080/bookings/list_by_company/" + companyId + "?" + params.toString())
      .then((res) => {
        return res;
      });
  }


  get_buildings() {
    return axios.get("http://localhost:8080/buildings/list").then((res) => {
      return res;
    });
  }

  get_workstations(
    floorId,
    buildingId,
    equipment = null,
    isPresentWindow = null,
  ) {
    return axios
      .get("http://localhost:8080/workstations", {
        params: {
          floorId: floorId,
          buildingId: buildingId,
          equipment: equipment,
          isPresentWindow: isPresentWindow,
        },
      })
      .then((res) => {
        return res;
      });
  }
}

export default new ApiService();
