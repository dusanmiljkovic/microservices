import React, {Component} from 'react'
import DepartmentService from '../../services/DepartmentService'

class CreateDepartmentComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.saveOrganization = this.saveOrganization.bind(this);
    }

    saveOrganization = (e) => {
        e.preventDefault(); 
        let department = {name: this.state.name, organizationId: this.state.id};
        console.log('department => ' + JSON.stringify(department));

        DepartmentService.addDepartment(department).then((res) => {
            this.props.history.push(`/organizations/${this.state.id}`);
        });
    }

    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }

    cancel (){
        this.props.history.push(`/organizations/${this.state.id}`);
    }
    
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add department</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
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

export default CreateDepartmentComponent