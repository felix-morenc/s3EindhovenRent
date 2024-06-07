import axios from 'axios';
import authHeader from './auth-header';

const Backend_URL = "http://localhost:8080/api/auth/";

class UserService {
    getPublicContent() {
      return axios.get(Backend_URL + 'all');
    }
  
    getUserBoard() {
      return axios.get(Backend_URL + 'user', { headers: authHeader() });
    }
  
    getStaffBoard() {
      return axios.get(Backend_URL + 'staff', { headers: authHeader() });
    }
  
  }

  export default new UserService();