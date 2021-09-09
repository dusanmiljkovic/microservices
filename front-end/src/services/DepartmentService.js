import axios from 'axios';

const DEPARTMENT_SERVICE_URL = "http://localhost:9002";

class OrganizationService {
    getDepartments(){
        return axios.get(DEPARTMENT_SERVICE_URL);
    }
    addDepartment(department){
        return axios.post(DEPARTMENT_SERVICE_URL, department);
    }
    updateDepartment(departmentId, department){
        return axios.put(DEPARTMENT_SERVICE_URL + '/' + departmentId, department);
    }
    getDepartmentById(departmentId){
        return axios.get(DEPARTMENT_SERVICE_URL + '/' + departmentId);
    }
    deleteDepartmentById(departmentId){
        return axios.delete(DEPARTMENT_SERVICE_URL + '/' + departmentId);
    }
    getDepartmentByIdWithEmployees(departmentId){
        return axios.get(DEPARTMENT_SERVICE_URL + '/' + departmentId + '/with-employees');
    }
    getDepartmentsByOrganization(departmentId){
        return axios.get(DEPARTMENT_SERVICE_URL + '/organization/' + departmentId);
    }
    getDepartmentsByOrganizationWithEmployees(departmentId){
        return axios.get(DEPARTMENT_SERVICE_URL + '/organization/' + departmentId + '/with-employees');
    }
}

export default new OrganizationService()