import React, {Component} from 'react'
import EmployeeService from '../../services/EmployeeService'

class CreateEmployeeComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            organizationId: this.props.match.params.organizationId,
            departmentId: this.props.match.params.id,
            name: '',
            position: '',
            dob: '',
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changePositionHandler = this.changePositionHandler.bind(this);
        this.changeDobHandler = this.changeDobHandler.bind(this);
        this.saveOrganization = this.saveOrganization.bind(this);
    }

    saveOrganization = (e) => {
        e.preventDefault(); 
        let employee = {
            name: this.state.name, 
            position: this.state.position, 
            dob: this.state.dob, 
            organizationId: this.state.organizationId,
            departmentId: this.state.departmentId
        };
        console.log('employee => ' + JSON.stringify(employee));

        EmployeeService.addEmployee(employee).then((res) => {
            this.props.history.push(`/organizations/${this.state.organizationId}/departments/${this.state.departmentId}`);
        });
    }

    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }
    changePositionHandler = (event) => {
        this.setState({position: event.target.value});
    }
    changeDobHandler = (event) => {
        this.setState({dob: event.target.value});
    }

    cancel (){
        this.props.history.push(`/organizations/${this.state.organizationId}/departments/${this.state.departmentId}`);
    }
    
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add employee</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Position: </label>
                                        <input placeholder="Position" name="position" className="form-control"
                                            value={this.state.position} onChange={this.changePositionHandler}/>
                                    </div>
                                    <div className="form-group">    
                                        <label>Date of birth: </label>
                                        <input placeholder="Date of birth" name="dob" className="form-control" type="date"
                                            value={this.state.dob} onChange={this.changeDobHandler}/>
                                    </div>
                                   
                                        {/* <DateTimePicker
                                            name="dot"
                                            className="form-control" 
                                            value={this.state.dot}
                                        />     */}
                                    <button className="btn btn-success" onClick={this.saveOrganization}>Save</button>
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

export default CreateEmployeeComponent