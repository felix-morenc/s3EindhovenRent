import React, { useState, useEffect } from "react";

import VehicleService from "../services/vehicle.service";

class VehicleComponent extends React.Component
{
    constructor(props)
    {
        super(props) 
        this.state = {vehicles:[]}
    }

    componentDidMount()
    {
        VehicleService.getVehicles().then((response) =>
        {
            this.setState({vehicles: response.data})
        })
    }

    viewVehicle(id)
    {
        this.props.history.push(`/view-vehicle/${id}`);
    }

    render()
    {
        return(
            <div>
                <h1 className = "text-center"> Vehicles For Rent</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td>Vehicle ID</td>
                            <td>Vehicle Name</td>
                            <td>Vehicle Price(day)</td>
                            <td>Vehicles Available</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.vehicles.map(
                                vehicle =>
                                <tr key = {vehicle.id}>
                                    <td>{vehicle.id}</td>
                                    <td>{vehicle.name}</td>
                                    <td>{vehicle.price}</td>
                                    <td>{vehicle.quantity}</td>
                                    <td>
                                        <button onClick={ () => this.viewVehicle(vehicle.id)}>View</button>

                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>
        )
    }

}

export default VehicleComponent