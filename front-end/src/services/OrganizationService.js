import axios from 'axios';

const ORGANIZATION_SERVICE_URL = "http://localhost:9003";

class OrganizationService {
    getOrganizations(){
        return axios.get(ORGANIZATION_SERVICE_URL);
    }
    addOrganization(organization){
        return axios.post(ORGANIZATION_SERVICE_URL, organization);
    }
    updateOrganization(organizationId, organization){
        return axios.put(ORGANIZATION_SERVICE_URL + '/' + organizationId, organization);
    }
    getOrganizationById(organizationId){
        return axios.get(ORGANIZATION_SERVICE_URL + '/' + organizationId);
    }
    deleteOrganizationById(organizationId){
        return axios.delete(ORGANIZATION_SERVICE_URL + '/' + organizationId);
    }
    getOrganizationByIdWithDepartments(organizationId){
        return axios.get(ORGANIZATION_SERVICE_URL + '/' + organizationId + '/with-departments');
    }
    getOrganizationByIdWithDepartmentsAndEmployees(organizationId){
        return axios.get(ORGANIZATION_SERVICE_URL + '/' + organizationId + '/with-departments-and-employees');
    }
    getOrganizationByIdWithEmployees(organizationId){
        return axios.get(ORGANIZATION_SERVICE_URL + '/' + organizationId + '/with-employees');
    }
}

export default new OrganizationService()