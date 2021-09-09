import React, {Component} from 'react'
import DepartmentService from '../../services/DepartmentService';
import OrganizationService from '../../services/OrganizationService'


class ViewOrganizationComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            id: this.props.match.params.id,
            organization: {},
            departments: []
        }

        this.addDepartment = this.addDepartment.bind(this);
        this.updateDepartment = this.updateDepartment.bind(this);
        this.viewDepartment = this.viewDepartment.bind(this);
        this.deleteDepartment = this.deleteDepartment.bind(this);
    }

    componentDidMount(){
        OrganizationService.getOrganizationByIdWithDepartmentsAndEmployees(this.state.id).then((res) => {
            this.setState({
                organization: res.data,
                departments: res.data.departments
            });
        });
    }

    addDepartment(){
        this.props.history.push(`/organizations/${this.state.id}/add`);
    }

    updateDepartment(departmentId){
        this.props.history.push(`/organizations/${this.state.id}/departments/${departmentId}/update`);
    }

    viewDepartment(departmentId){
        this.props.history.push(`/organizations/${this.state.id}/departments/${departmentId}`);
    }

    deleteDepartment(departmentId){
        DepartmentService.deleteDepartmentById(departmentId).then( res => {
            this.setState({departments: this.state.departments.filter(department =>  department.id !== departmentId)});
        })
    }

    cancel (){
        this.props.history.push('/organizations');
    }

    body(value){
        if (value !== 'undefined' ) {
             console.log(value);
        }
    }
    
    render() {
        return (
            <div>
                <div className="container card">
                    <div className="card-body">
                        <div className="row">
                            <div className="col-md-12">
                                <h2 className="text-center">Organization</h2>
                                <div className="card-body">
                                    <p>Name: {this.state.organization.name}</p>
                                    <p>Address: {this.state.organization.address}</p>
                                </div>
                            </div>
                        </div>
                        
                        <div className="row">
                            <h3>Departments</h3>
                            <div>
                                <button className="btn btn-primary" onClick={this.addDepartment}>Add department</button>
                                <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Back</button>
                            </div>
                            <table className="table trable-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Department name</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                {
                                    this.state.departments.map(
                                        department => 
                                        <tr key = {department.id}>
                                            <td> {department.name} </td>
                                            <td>
                                                <button onClick = { () => this.updateDepartment(department.id) } className="btn btn-info">Update</button>
                                                <button onClick = { () => this.viewDepartment(department.id) } className="btn btn-info" style={{marginLeft:10}}>View</button>
                                                <button onClick = { () => this.deleteDepartment(department.id) } className="btn btn-danger" style={{marginLeft: "10px"}}>Delete</button>
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

export default ViewOrganizationComponent