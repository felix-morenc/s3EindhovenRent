import axios from "axios";

const Backend_URL = "http://localhost:8080/api/auth/";

const register = (username, email, password) => 
{
    return axios.post(Backend_URL + "signup", {username, email, password});
};

const login = (username, password) =>
{
    return axios
      .post(Backend_URL + "signin", {username,password,})
      .then((response) => {
        if (response.data.accessToken)
        {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
};

const logout = () => {
    localStorage.removeItem("user");
  };
  
  const getUser = () => {
    return JSON.parse(localStorage.getItem("user"));
  };
  
  export default {
    register,
    login,
    logout,
    getUser,
  };
