import axios from 'axios';
import authHeader from './auth-header';

const VEHICLES_REST_API_URL = 'http://localhost:8080'

class VehicleService {
    getVehicles() {
      return axios.get(VEHICLES_REST_API_URL + '/vehicles', { headers: authHeader() });
    }
    getVehicleById(vehicleId){
      return axios.get(VEHICLES_REST_API_URL + '/vehicles/' + vehicleId , { headers: authHeader() });
    }

    createVehicle(vehicle)
    {
      return axios.post(VEHICLES_REST_API_URL + '/addVehicle', vehicle, { headers: authHeader() })
    }

    updateVehicle(vehicle)
    {
      return axios.put(VEHICLES_REST_API_URL + '/updateVehicle', vehicle, { headers: authHeader() })
    }

    deleteVehicle(vehicleid)
    {
      return axios.delete(VEHICLES_REST_API_URL + '/deleteVehicle/' + vehicleid, { headers: authHeader() })
    }
  }

  export default new VehicleService();