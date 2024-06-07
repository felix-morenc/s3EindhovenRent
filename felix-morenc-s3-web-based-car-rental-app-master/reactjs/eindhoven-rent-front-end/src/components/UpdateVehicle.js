import React, { Component } from 'react'
import vehicleService from '../services/vehicle.service';

class UpdateVehicle extends React.Component
{
    constructor(props)
    {
        super(props) 
        this.state =
        {
            id: this.props.match.params.id,
            name: '',
            price: '',
            quantity: ''
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changePriceHandler = this.changePriceHandler.bind(this);
        this.changeQuantityHandler = this.changeQuantityHandler.bind(this);
        this.updateVehicle = this.updateVehicle.bind(this);
        
        
    }

    componentDidMount(){
        vehicleService.getVehicleById(this.state.id).then((res) => 
        {
            let vehicle = res.data;
            this.setState({name: vehicle.name, price: vehicle.price, quantity: vehicle.quantity})
        })
    }

    updateVehicle = (e) =>
    {
        e.preventDefault();
        let vehicle = {id: this.state.id, name: this.state.name, price: this.state.price, quantity: this.state.quantity};
        console.log('vehicle => ' + JSON.stringify(vehicle));
        vehicleService.updateVehicle(vehicle).then( res => { this.props.history.push('/manage-vehicles') } );
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
                            <h2 className = "text-center">Update Vehicle</h2>
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
                                    <button className = "btn btn-success" onClick = {this.updateVehicle}>Update Vehicle</button>
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

export default UpdateVehicle