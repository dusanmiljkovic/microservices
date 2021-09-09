import React, {Component} from 'react'
import OrganizationService from '../../services/OrganizationService'


class UpdateOrganizationComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            address: ''
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.updateOrganization = this.updateOrganization.bind(this);
    }

    componentDidMount(){
        OrganizationService.getOrganizationById(this.state.id).then((res) => {
            let organizations = res.data;
            this.setState({
                name: organizations.name,
                address: organizations.address
            });
        });
    }


    updateOrganization = (e) => {
        e.preventDefault(); 
        let organization = {name: this.state.name, address: this.state.address};
        console.log('organization => ' + JSON.stringify(organization));

        OrganizationService.updateOrganization(this.state.id, organization).then((res) => {
            this.props.history.push('/organizations');
        });
    }

    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }
    changeAddressHandler = (event) => {
        this.setState({address: event.target.value});
    }

    cancel (){
        this.props.history.push('/organizations');
    }
    
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update organization</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Address: </label>
                                        <input placeholder="Address" name="address" className="form-control"
                                            value={this.state.address} onChange={this.changeAddressHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateOrganization}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default UpdateOrganizationComponent