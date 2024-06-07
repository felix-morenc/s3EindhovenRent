import axios from 'axios';
import authHeader from './auth-header';

const RESERVATIONS_REST_API_URL = 'http://localhost:8080'

class ReservationService {
    getReservations() {
      return axios.get(RESERVATIONS_REST_API_URL + "/reservations", { headers: authHeader() });
    }
    createReservation(reservation) {
      return axios.post(RESERVATIONS_REST_API_URL + "/addReservation", reservation, { headers: authHeader() });
    }
  }

  export default new ReservationService();