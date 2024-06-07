import React, { Component } from 'react'
import vehicleService from '../services/vehicle.service';

class AddVehicle extends React.Component
{
    constructor(props)
    {
        super(props) 
        this.state =
        {
            name: '',
            price: '',
            quantity: ''
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeQuantityHandler = this.changeQuantityHandler.bind(this);
        this.addVehicle = this.addVehicle.bind(this);
        
        
    }

    addVehicle = (e) =>
    {
        e.preventDefault();
        let vehicle = {name: this.state.name, price: this.state.price, quantity: this.state.quantity};
        console.log('vehicle => ' + JSON.stringify(vehicle));
        vehicleService.createVehicle(vehicle).then( res => { this.props.history.push('/manage-vehicles') } );
    }

    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }

    changePriceHandler = (event) => {
        this.setState({price: event.target.value});
    }
    changeQuantityHandler = (event) => {
        this.setState({quantity: event.target.value});
    }

    render()
    {
        return(
            <div>
                <div className="container">
                    <div className = "row">
                        <div className = "card">
                            <h2 className = "text-center">Add Vehicle to Database</h2>
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label>Vehicle Name:</label>
                                        <input placeholder = "Car Name" name = "name" className="form-control" value={this.state.name} onChange={this.changeNameHandler}></input>
                                    </div>
                                    <div className = "form-group">
                                        <label>Price:</label>
                                        <input placeholder = "Price" name = "price" className="form-control" value={this.state.price} onChange={this.changePriceHandler}></input>
                                    </div>
                                    <div className = "form-group">
                                        <label>Quantity:</label>
                                        <input placeholder = "Quantity" name = "quantity" className="form-control" value={this.state.quantity} onChange={this.changeQuantityHandler}></input>
                                    </div>
                                    <button className = "btn btn-success" onClick = {this.addVehicle}>Add Vehicle</button>
                                    <button className = "btn btn-success" onClick = { () => this.props.history.push('/manage-vehicles')} style = {{marginLeft: "20px"}}>Back</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        )
    }

}

export default AddVehicle