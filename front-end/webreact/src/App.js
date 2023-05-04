import logo from './logo.svg';
import './App.css';
import { Routes, Route } from "react-router-dom"
import { useEffect } from 'react';
import { useLocalState } from './util/useLocalStorage';
import Dashboard from './Dashboard';
import Homepage from './Homepage';
import Login from './Login';
import PrivateRoute from './PrivateRoute';

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
        path="/dashboard" //wrap dashboard into PrivateRoute
        element={
          <PrivateRoute>
            <Dashboard />
          </PrivateRoute>
        }
      />
      <Route path="/login" element={<Login/>} />
      <Route path="/" element={<Homepage/>} />
    </Routes> 
  );
}

export default App;
