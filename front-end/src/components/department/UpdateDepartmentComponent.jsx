import React, {Component} from 'react'
import DepartmentService from '../../services/DepartmentService'

class UpdateDepartmentComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            organizationId: this.props.match.params.organizationId,
            id: this.props.match.params.id,
            name: '',
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.updateDepartment = this.updateDepartment.bind(this);
    }

    componentDidMount(){
        DepartmentService.getDepartmentById(this.state.id).then((res) => {
            let organizations = res.data;
            this.setState({
                name: organizations.name,
            });
        });
    }


    updateDepartment = (e) => {
        e.preventDefault(); 
        let department = {organizationId: this.state.organizationId ,name: this.state.name};
        console.log('department => ' + JSON.stringify(department));

        DepartmentService.updateDepartment(this.state.id, department).then((res) => {
            this.props.history.push(`/organizations/${this.state.organizationId}`);
        });
    }

    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }
    changeAddressHandler = (event) => {
        this.setState({address: event.target.value});
    }

    cancel (){
        this.props.history.push(`/organizations/${this.state.organizationId}`);
    }
    
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update department</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateDepartment}>Save</button>
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

export default UpdateDepartmentComponent