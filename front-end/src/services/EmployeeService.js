import axios from 'axios';

const EMPLOYEE_SERVICE_URL = "http://localhost:9001";

class EmployeeSeervice {
    getEmployees(){
        return axios.get(EMPLOYEE_SERVICE_URL);
    }
    addEmployee(employee){
        return axios.post(EMPLOYEE_SERVICE_URL, employee);
    }
    getEmployeeById(employeeId){
        return axios.get(EMPLOYEE_SERVICE_URL + '/' + employeeId);
    }
    deleteEmployeeById(employeeId){
        return axios.delete(EMPLOYEE_SERVICE_URL + '/' + employeeId);
    }
    updateEmployee(employeeId, employee){
        return axios.put(EMPLOYEE_SERVICE_URL + '/' + employeeId, employee);
    }
    getEmployeesByDepartment(departmentId){
        return axios.get(EMPLOYEE_SERVICE_URL + '/department/' + departmentId);
    }
    getEmployeesByOrganization(organizationId){
        return axios.get(EMPLOYEE_SERVICE_URL + '/organization/' + organizationId);
    }
}

export default new EmployeeSeervice()