import * as React from 'react';
import './App.css';
import EmployeeList from './EmployeeList';
import FileUpload from './FileUpload';

interface Employee {
  id: string,
  firstName: string;
  lastName: string;
  address: string;
  phoneNumber: string;
}

interface AppState {
  employees: Array<Employee>;
  isLoading: boolean;
}

class App extends React.Component<{}, AppState> {
  constructor(props = {}) {
    super(props);

    this.state = { 
      employees: [],
      isLoading: false 
    }

    this.onUploadStart = this.onUploadStart.bind(this);
    this.onUploadEnd = this.onUploadEnd.bind(this);
  }

  render() {
    const {employees, isLoading} = this.state;

    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Excel Upload Demo</h1>
        </header>
        <FileUpload
          url="http://localhost:8080/upload"
          onUploadStart={this.onUploadStart}
          onUploadEnd={this.onUploadEnd} />
        <EmployeeList
          employees={employees}
          isLoading={isLoading}/>
      </div>
    );
  }

  onUploadStart() {
    this.setState({ isLoading: true })
  }

  onUploadEnd(err: any, res: any) {
    // tslint:disable-next-line:no-console
    console.log(res);

    const employees = err ? [] : res.body;
    this.setState({ 
      employees,
      isLoading: false
    });

    if (err) {
      alert(err);
    }
  }

}

export default App;