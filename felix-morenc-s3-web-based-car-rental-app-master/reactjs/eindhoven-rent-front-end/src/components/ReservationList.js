import React, { useState, useEffect } from "react";

import ReservationService from "../services/reservation.service";

class ReservationComponent extends React.Component
{
    constructor(props)
    {
        super(props) 
        this.state = {reservations:[]}
    }

    componentDidMount()
    {
        ReservationService.getReservations().then((response) =>
        {
            this.setState({reservations: response.data})
        })
    }

    render()
    {
        return(
            <div>
                <h1 className = "text-center"> All Reservations</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td>Reservation ID</td>
                            <td>Price</td>
                            <td>User ID</td>
                            <td>Vehicle ID</td>
                            <td>Vehicle Name</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.reservations.map(
                                reservation =>
                                <tr key = {reservation.id}>
                                    <td>{reservation.id}</td>
                                    <td>{reservation.price}</td>
                                    <td>{reservation.user_id}</td>
                                    <td>{reservation.vehicle_id}</td>
                                    <td>{reservation.vehicle_name}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>
        )
    }

}

export default ReservationComponent