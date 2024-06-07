import React, { useState, useEffect } from "react";
import vehicleService from "../services/vehicle.service";
import AuthService from "../services/auth.service";
import reservationService from "../services/reservation.service";



class ViewVehicle extends React.Component
{
    constructor(props)
    {
        super(props) 
        this.state =
        {
            id: this.props.match.params.id,
            vehicle: {},
        }
        this.makeReservation = this.makeReservation.bind(this);
        
    }

    componentDidMount()
    {
        vehicleService.getVehicleById(this.state.id).then( res => {
            this.setState({vehicle: res.data})
        })
        
    }

    makeReservation = (e) =>
    {
        e.preventDefault();
        const user = AuthService.getUser();
        let reservation = {userId: user.id, vehicleId: this.state.vehicle.id, vehicleName:this.state.vehicle.name, price: this.state.vehicle.price}; 
        console.log(' reservation => ' + JSON.stringify(reservation) );
        reservationService.createReservation(reservation).then(this.props.history.push('/manage-vehicles'))
    }


    render()
    {
        return(
            <div>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center">Would you like to reserve this vehicle?</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label>Vehicle Name: </label>
                            <div>{this.state.vehicle.name}</div>
                        </div>
                    </div>
                    <div className = "card-body">
                        <div className = "row">
                            <label>Vehicle Price: </label>
                            <div>{this.state.vehicle.price}</div>
                        </div>
                    </div>
                    <div className = "card-body">
                        <div className = "row">
                            <label>Vehicles Available: </label>
                            <div>{this.state.vehicle.quantity}</div>
                        </div>
                    </div>
                    <div className = "card-body">
                        <div className = "row">
                            <form>
                                <button onClick={this.makeReservation}>Reserve Vehicle</button>
                            </form>
                        </div>
                    </div>

                </div>

            </div>
        )
    }

}

export default ViewVehicle