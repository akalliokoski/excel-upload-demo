import * as React from 'react';

interface Employee {
  id: string,
  firstName: string;
  lastName: string;
  address: string;
  phoneNumber: string;
}

interface EmployeeListProps {
  employees: Array<Employee>;
  isLoading: boolean;
}

interface EmployeeListState {
}

class EmployeeList extends React.Component<EmployeeListProps, EmployeeListState> {

  constructor(props: EmployeeListProps) {
    super(props);
  }

  render() {
    const {employees, isLoading} = this.props;
    if (employees.length === 0) {
      return <div />
    }

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="employee-list">
        <table className="table table-striped">
          <thead>
            <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Address</th>
                <th scope="col">Phone Number</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee: Employee) =>
              <tr key={employee.id}>
                <td scope="row">{employee.firstName}</td>
                <td>{employee.lastName}</td>
                <td>{employee.address}</td>
                <td>{employee.phoneNumber}</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default EmployeeList;