import React from 'react';
import './App.css';
//import logo from './logo.svg';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import HeaderComponent from '../src/components/HeaderComponent';
import ListOrganizationsComponent from './components/organization/ListOrganizationsComponent';
import CreateOrganizationComponent from './components/organization/CreateOrganizationComponent';
import UpdateOrganizationComponent from './components/organization/UpdateOrganizationComponent';
import ViewOrganizationComponent from './components/organization/ViewOrganizationComponent';
import CreateDepartmentComponent from './components/department/CreateDepartmentComponent';
import ViewDepartmentComponent from './components/department/ViewDepartmentComponent';
import UpdateDepartmentComponent from './components/department/UpdateDepartmentComponent';
import CreateEmployeeComponent from './components/employee/CreateEmployeeComponent';
import UpdateEmployeeComponent from './components/employee/UpdateEmployeeComponent';
import FooterComponent from '../src/components/FooterComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent/>
        <div className="container">
          <Switch>
            <Route path="/" exact component={ListOrganizationsComponent}></Route>

            <Route path="/organizations/:organizationId/departments/:departmentId/employees/:id/update" component={UpdateEmployeeComponent}></Route>
            <Route path="/organizations/:organizationId/departments/:id/add" component={CreateEmployeeComponent}></Route>

            <Route path="/organizations/:organizationId/departments/:id/update" component={UpdateDepartmentComponent}></Route>
            <Route path="/organizations/:organizationId/departments/:id" component={ViewDepartmentComponent}></Route>
            <Route path="/organizations/:id/add" component={CreateDepartmentComponent}></Route>

            <Route path="/organizations/add" component={CreateOrganizationComponent}></Route>
            <Route path="/organizations/:id/update" component={UpdateOrganizationComponent}></Route>
            <Route path="/organizations/:id" component={ViewOrganizationComponent}></Route>
            <Route path="/organizations" component={ListOrganizationsComponent}></Route>
          </Switch>
        </div>
        <FooterComponent/>
      </Router>
    </div>
  );
}

export default App;
