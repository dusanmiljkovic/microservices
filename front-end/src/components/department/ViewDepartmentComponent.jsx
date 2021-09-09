import React, {Component} from 'react'
import DepartmentService from '../../services/DepartmentService'
import EmployeeService from '../../services/EmployeeService'
import OrganizationService from '../../services/OrganizationService'


class ViewDepartmentComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            organizationId: this.props.match.params.organizationId,
            id: this.props.match.params.id,
            organization: {},
            department: {},
            employees: []
        }

        this.addEmpoyee = this.addEmpoyee.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }

    componentDidMount(){
        DepartmentService.getDepartmentByIdWithEmployees(this.state.id).then((res) => {
            this.setState({
                department: res.data,
                organizationId : res.data.organizationId,
                employees: res.data.employees
            });
        });
        OrganizationService.getOrganizationById(this.state.organizationId).then((res) => {
            this.setState({
                organization: res.data,
            });
        });
    }

    addEmpoyee(){
        this.props.history.push(`/organizations/${this.state.organizationId}/departments/${this.state.id}/add`);
    }

    updateEmployee(employeeId){
        this.props.history.push(`/organizations/${this.state.organizationId}/departments/${this.state.id}/employees/${employeeId}/update`);
    }

    deleteEmployee(employeeId){
        EmployeeService.deleteEmployeeById(employeeId).then( res => {
            this.setState({employees: this.state.employees.filter(employee =>  employee.id !== employeeId)});
        })
    }

    body(value){
        if (value !== 'undefined' ) {
             console.log(value);
        }
    }

    link(){
        let address = `/organizations/${this.state.organizationId}`;
        return <a href={address}>{this.state.organization.name}</a>
    }

    cancel(){
        this.props.history.push(`/organizations/${this.state.organizationId}`);
    }
    
    render() {
        return (
            <div>
                <div className="container card">
                    <div className="card-body">
                        <div className="row">
                            <div className="col-md-12">
                                <h2 className="text-center">Department</h2>
                                <div className="card-body">
                                    <p>Organization: {this.link()}</p>
                                    <p>Name: {this.state.department.name}</p>
                                </div>
                            </div>
                        </div>
                        
                        <div className="row">
                            <h3>Employee</h3>
                            <div>
                                <button className="btn btn-primary" onClick={this.addEmpoyee}>Add employee</button>
                                <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Back</button>
                            </div>
                            <table className="table trable-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Position</th>
                                        <th>Date of birth</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {
                                    this.state.employees.map(
                                        employee => 
                                        <tr key = {employee.id}>
                                            <td> {employee.name} </td>
                                            <td> {employee.position} </td>
                                            <td> {employee.dob} </td>
                                            <td>
                                                <button onClick = { () => this.updateEmployee(employee.id) } className="btn btn-info">Update</button>
                                                <button onClick = { () => this.deleteEmployee(employee.id) } className="btn btn-danger" style={{marginLeft: "10px"}}>Delete</button>
                                            </td>
                                        </tr>
                                    )
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewDepartmentComponent