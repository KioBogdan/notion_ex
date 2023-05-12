import logo from './logo.svg';
import './App.css';
import { Routes, Route } from "react-router-dom"
import { useEffect } from 'react';
import { useLocalState } from './util/useLocalStorage';
import FinancialActivities from './FinancialActivities';
import Homepage from './Homepage';
import Login from './Login';
import PrivateRoute from './PrivateRoute';
import ProjectActivities from './ProjectActivities';
import ReadActivities from './ReadActivities';
import ToDoActivities from './ToDoActivities';
import Register from './Register';

function App() {

  // const [jwt, setJwt] = useLocalState("", "jwt"); //init a value, initially empty, then inject jwt 

  // useEffect(() => {
  //   if(!jwt) { 
  //   console.log("Hello!");
  //   const reqBody = {
  //     email: "bogdan_chiorean57@yahoo.com",
  //     password: "1234"
  //   }
  
  //   fetch("/api/auth/login", {
  //     headers: {
  //       "Content-Type" : "application/json",
  //     }, 
  //     method: "post",
  //     body: JSON.stringify(reqBody),
  //   })
  //     .then((response) => Promise.all([response.json(), response.headers]))
  //     .then(([body, headers]) => {
  //       setJwt(headers.get("authorization")); //async operation; will not see the result just after it finishes
  //       window.location.href = "dashboard"
  //     });
  //   }
  // }, []); //inject jwt dependency

  // useEffect(() => {
  //   console.log(`Jwt value is ${jwt}`);
  // }, [jwt]);

  return (
    <Routes>
      <Route 
        path="/financial" //wrap dashboard into PrivateRoute
        element={
          <PrivateRoute>
            <FinancialActivities />
          </PrivateRoute>
        }
      />
      <Route 
        path="/project" //wrap dashboard into PrivateRoute
        element={
          <PrivateRoute>
            <ProjectActivities />
          </PrivateRoute>
        }
      />
      <Route 
        path="/read" //wrap dashboard into PrivateRoute
        element={
          <PrivateRoute>
            <ReadActivities />
          </PrivateRoute>
        }
      />
      <Route 
        path="/todo" //wrap dashboard into PrivateRoute
        element={
          <PrivateRoute>
            <ToDoActivities />
          </PrivateRoute>
        }
      />
      <Route path="/register" element={<Register/>} />
      <Route path="/login" element={<Login/>} />
      <Route path="/" element={<Homepage/>} />
    </Routes> 
  );
}

export default App;
