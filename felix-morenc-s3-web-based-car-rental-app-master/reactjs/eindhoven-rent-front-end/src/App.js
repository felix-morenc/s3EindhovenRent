import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import AuthService from "./services/auth.service";

import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";
import VehicleList from "./components/VehicleList";
import ReservationList from "./components/ReservationList";
import ViewVehicle from './components/ViewVehicle';
import ManageVehicleComponent from './components/ManageVehicles';
import AddVehicle from './components/AddVehicle';
import UpdateVehicle from './components/UpdateVehicle';

const App = () => {

  const [showManagementList, setShowManagementList] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getUser();

    if (user)
    {
      setCurrentUser(user);
      setShowManagementList(user.roles.includes("ROLE_STAFF"));     
    }

  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          Eindhoven Rent
        </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Home
            </Link>
          </li>

          {showManagementList && (
            <li className="nav-item">
              <Link to={"/reservations"} className="nav-link">
                All Reservations
              </Link>
            </li>
            
          )}


          {showManagementList && (
            <li className="nav-item">
              <Link to={"/manage-vehicles"} className="nav-link">
                Manage Vehicles
              </Link>
            </li>
            
          )}          

          {currentUser && (
            <li className="nav-item">
              <Link to={"/vehicles"} className="nav-link">
                Vehicles
              </Link>
            </li>
          )}
        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Sign Up
              </Link>
            </li>
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/home"]} exact component={Home} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <Route exact path="/profile" component={Profile} />
          <Route path="/vehicles" component={VehicleList} />
          <Route path="/manage-vehicles" component={ManageVehicleComponent} />
          <Route path="/reservations" component={ReservationList} />
          <Route path="/view-vehicle/:id" component={ViewVehicle} />
          <Route exact path="/add-vehicle" component={AddVehicle} />
          <Route exact path="/update-vehicle/:id" component={UpdateVehicle} />
        </Switch>
      </div>
    </div>
  );
  
}

export default App;
