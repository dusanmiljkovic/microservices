import React, {Component} from 'react'
import OrganizationService from '../../services/OrganizationService'

class ListOrganizationsComponent extends Component{
    constructor(props) {
        super (props)

        this.state = {
            organizations : []
        }
        this.addOrganization = this.addOrganization.bind(this);
        this.updateOrdanization = this.updateOrganization.bind(this);
        this.viewOrganization = this.viewOrganization.bind(this);
        this.deleteOrganization = this.deleteOrganization.bind(this);
    }

    componentDidMount(){
        OrganizationService.getOrganizations().then((res) => {
            this.setState({
                organizations: res.data
            });
        });
    }

    addOrganization(){
        this.props.history.push('/organizations/add');
    }
    
    updateOrganization(organizationId){
        this.props.history.push(`/organizations/${organizationId}/update`);
    }

    deleteOrganization(organizationId){
        OrganizationService.deleteOrganizationById(organizationId).then( res => {
            this.setState({organizations: this.state.organizations.filter(organization =>  organization.id !== organizationId)});
        })
    }

    viewOrganization(organizationId){
        this.props.history.push(`/organizations/${organizationId}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Organizations list</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addOrganization}>Add organization</button>
                </div>
                <div className="row">
                    <table className="table trable-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Organization name</th>
                                <th>Organization address</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.organizations.map(
                                    organization => 
                                    <tr key = {organization.id}>
                                        <td> {organization.name} </td>
                                        <td> {organization.address} </td>
                                        <td>
                                            <button onClick = { () => this.updateOrganization(organization.id) } className="btn btn-info">Update</button>
                                            <button onClick = { () => this.viewOrganization(organization.id) } className="btn btn-info" style={{marginLeft:10}}>View</button>
                                            <button onClick = { () => this.deleteOrganization(organization.id) } className="btn btn-danger" style={{marginLeft: "10px"}}>Delete</button>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListOrganizationsComponent